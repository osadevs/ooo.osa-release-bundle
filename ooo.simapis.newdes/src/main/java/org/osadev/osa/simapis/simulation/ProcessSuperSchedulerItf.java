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

import org.objectweb.fractal.fraclet.annotations.Interface;



/**
 * The super-scheduler is in charge of the synchronization and coordination between the 
 * OSA SimulationModel components. 
 * 
 * <p>The SuperScheduler is a standard Fractal component. It is connected (bound) to
 * every {@link org.osadev.osa.simapis.modeling.AbstractProcessModel} component to which it provides a number of centralized
 * functions to ensure the proper execution of the simulation, such as:
 * <ul>
 * <li> Starting and stopping the simulation
 * <li> Keeping track of the global (virtual) time of the simulation
 * <li> Queuing and activating the components according to the time order of their
 * next pending events
 * <li> Synchronization of user defined wait queues
 * </ul> 
 * 
 * 
 * @author odalle
 * @author jribault
 */
@Interface(name = "superschedulersvc")
public interface ProcessSuperSchedulerItf<U extends Comparable<U>> 
extends EventSuperSchedulerItf<U> {

	
	public void parkSchedulerThread(String trace);
	
	
	public void resumeSchedulerThread();
	
	public boolean iterateReleaseOneOnCondition(String condition, String param);
	
	public int iterateReleaseAllOnCondition(String condition, String param);

	/**
	 * This method is used by a simulation component to report its first
	 * pending event in its local scheduler. 
	 * @param simulationController
	 *        Simulation interface of the simulation component
	 * @param wakeUpTime
	 *        Time of the first pending event
	 * @param type
	 * 		  Type of event
	 * @param eventId
	 * 		  Event id (assigned independently by each simulation 
	 *        component.)
	 * @param previousTime
	 *        The wake up time used the last time this method was called by
	 *        the same simulation controller
	 */
	//void wakeMeUp(final ProcessSimulationControllerAPI<U> simulationController,
	//		final ModelingTime<U> wakeUpTime,	final long eventId, final ModelingTime<U> previousTime);
	
	
	/**
	 * This method is called by the simulation component to register a new 
	 * wait condition after a call to {@link ProcessModelingAPI#waitOnConditionForDelay(String, long)}
	 * 
	 * @param simulationController
	 * @param condition
	 */
	//void wakeMeUpCond(final ProcessSimulationControllerAPI<U> simulationController, final String condition);
	
	
	/**
	 * This method is called by the simulation component to un-register a
	 * wait condition after the last blocked thread on this condition has
	 * resumed execution
	 * 
	 * @param simulationController
	 * @param condition
	 */
	//void cancelWaitCond(final ProcessSimulationControllerAPI<U> simulationController, final String condition);
	
	
}
