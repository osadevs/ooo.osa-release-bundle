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
import org.osadev.osa.simapis.modeling.ModelingTimeAPI;
import org.osadev.osa.simapis.modeling.SimulationTimeAPI;

/**
 * The super-scheduler is in charge of the synchronization and coordination between the 
 * OSA SimulationModel components. 
 * 
 * <p>The SuperScheduler is a standard Fractal component. It is connected (bound) to
 * every {@link org.osadev.osa.simapis.modeling.AbstractEventModel} component to which it provides a number of centralized
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
public interface EventSuperSchedulerItf<U extends Comparable<U>> extends SimulationTimeAPI<U>
{	
	
	/**
	 * Request to be put in the global wait queue until the given time.
	 * 
	 * <p> A simulation controller can be put at most once in the global wait queue. 
	 * Any subsequent call to this method before the controller gets called back will
	 * result in overriding the previous call. For this reason, it is expcted that each
	 * simulation controller keeps track of the previous calls and filters request such
	 * that a later request does not cancel an earlier one.
	 * 
	 * @param time
	 * @param callBack
	 */
	public void waitUntil(ModelingTimeAPI<U> time, EventSimulationControllerAPI<U> callBack);
	
	
}
