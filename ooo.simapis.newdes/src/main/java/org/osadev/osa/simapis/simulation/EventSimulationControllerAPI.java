/**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--> 
<!--                Open Simulation Architecture (OSA)                  -->
<!--                                                                    -->
<!--      This software is distributed under the terms of the           -->
<!--           CECILL-C FREE SOFTWARE LICENSE AGREEMENT                 -->
<!--  (see http://www.cecill.info/licences/Licence_CeCILL-C_V1-en.html) -->
<!--                                                                    -->
<!--  Copyright © 2006-2015 Université Nice Sophia Antipolis            -->
<!--  Contact author: Olivier Dalle (olivier.dalle@unice.fr)            -->
<!--                                                                    -->
<!--  Parts of this software development were supported and hosted by   -->
<!--  INRIA from 2006 to 2015, in the context of the common research    -->
<!--  teams of INRIA and I3S, UMR CNRS 7172 (MASCOTTE, COATI, OASIS and -->
<!--  SCALE).                                                           -->
<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++**/
package org.osadev.osa.simapis.simulation;

import org.osadev.osa.simapis.exceptions.SimSchedulingException;
import org.osadev.osa.simapis.modeling.ModelingTimeAPI;
import org.osadev.osa.simapis.modeling.SimulationTimeAPI;

/**
 * Control interface of the OSA simulation controller.
 * 
 * The simulation controller is in charge of implementing the simulation
 * engine in a distributed way, within the Fractal component membrane of 
 * each simulation model component.
 * 
 * The client of this interface is typically a super-scheduler component.
 * 
 * 
 * This API provides the means to initialize and start the simulation
 * service.
 * 
 * Here are time-sequences for various scenarios:
 * 
 * <ol type="A" > 
 *   <li> Initialization:
 *   
 *   <ol type="1">
 *   <li> Launcher thread creates an instance of the root model component (eg. using ADL factory)
 *   
 *   <li> Launcher thread creates a (super-)scheduler component and binds it to model components.
 *   
 *	<li> Launcher thread uses the model component LCC to start components
 *
 *	<li> Launcher thread calls the super-scheduler's (or root's) startSimulation()
 *  method
 *  
 *  <li> startSimulation calls each model primitive component's init() method
 *  
 *  <li> each model component with pending events registers the first pending event 
 *  using the wakeMeUp method
 *  
 *   </ol>
 *   
 *   <li> Main loop:
 * 
 * <p> 1. initial invariant state: super-scheduler thread ready for a new iteration,
 * all other simulation threads are sleeping (eg. blocked on a semaphore). All 
 * simulation components with at least one pending event are assumed to
 * have called wakeMeUp with parameters corresponding to first pending event (time,
 * event type, event id).
 * 
 * <p> 2. superscheduler.mainloop() looks for first entry in the global pending event set.
 * 
 * <p> 3-a. case ASYNC: super-scheduler CALLS simc resume(current_time)
 * 
 * <p> 4. (SC Thread) simC dequeues next pending event and advances local time
 * 
 * <p> 5. (SC Thread) simC looks up queue head and calls wakeMeUp with next entry
 * 
 * <p> 6. (async, SC Thread) simC starts a new thread and invokes the dequeued event 
 * execution
 * 
 * <p> 7-a. (SC Thread) resume returns true if a thread was created in the simulation component.
 * 
 * <p> 7-b. (simC Thread) The threads executes as long as possible for the 
 * current date until it either (8-a) terminates or it (8-b) reaches a 
 * simulation wait instruction
 * 
 * <p> 8-a. (simC Thread) the running thread calls the 
 * eventComplete(current time, sync=f)  
 * which wakes up the SC Thread while simC Thread releases itself.
 * 
 * <p> 8-b. (simC Thread, possibly in another simC) creates a wake up event 
 * for resuming execution later when the wait delay has expired. If the 
 * time of resuming is earlier than previous head of queue in current simC, 
 * simC thread calls wakeMeUp to update status. Finally, the simC thread calls
 * eventStopped(current time) and goes to sleep until the wake up event 
 * is called.
 * 
 * <p> 8. (async, SC Thread) superscheduler.main() goes to sleep until either 
 * eventComplete() or eventStopped() is called.
 * 
 * <p> 9. (SC Thread) eventComplete/eventStopped update the current 
 * simulation time and supersched.main() returns to 1.
 *  
 * <p> 3-b. case SYNC: super-scheduler calls simc resume(current_time, sync=t)
 * 
 * <p> 4. - 5. idem
 * 
 * <p> 6. (sync, SC thread) invokes the dequeued event 
 * 
 * <p> 7. (sync, SC thread) executes business code until 
 * eventComplete(current time, sync=t) is called. 
 * NB: the sync flag can be automatically assigned by assessing if
 * current thread == SC Thread. (eventStopped not allowed in sync mode)
 * 
 * <p> 8. (sync) jump to 9.
 * </ol>
 * 
 * <p>NB: Comparable is not strictly necessary, it is just to help using this type
 * in more cmplex ones that require natural ordering (eg. Guava's TreeMultimap).
 * 
 * @author odalle
 * 
 *
 */
public interface EventSimulationControllerAPI<U extends Comparable<U>>
extends SimulationTimeAPI<U>, Comparable<EventSimulationControllerAPI<U>>
{
	
	
	/**
	 * Initializes the simulation controller.
	 */
	void init();
	
	/**
	 * Request a simulation component to wake up and resume execution with
	 * next pending event.
	 * 
	 * The local simulation time value is updated to the given value, then the 
	 * first pending event is dequeued. If the scheduled time of this event 
	 * is different than the current time, a {@link SimSchedulingException}
	 * is raised.
	 * 
	 * @param currentTime
	 * 		The current simulation time <b>before</b> the next pending event is processed.
	 * @return
	 * 		True if a separate thread was created or continued to process the event 
	 *      (asynchronous processing).
	 * 
	 * @throws SimSchedulingException if an event is found pending for an
	 * 		different time than the given currentTime.
	 */
	public boolean resumeNext(ModelingTimeAPI<U> currentTime) throws SimSchedulingException;
	
	
	/**
	 * Gets the schedule time of the next pending event.
	 * 
	 * <p>If no event is pending, returns an infinite time.
	 * 
	 * @return time of next pending event
	 */
	public ModelingTimeAPI<U> getNextScheduleTime();
	
	
	/**
	 * Stop this simulation controller.
	 */
	void quit();

}
