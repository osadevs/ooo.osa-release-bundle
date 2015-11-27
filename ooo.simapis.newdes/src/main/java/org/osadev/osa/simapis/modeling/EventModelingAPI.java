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
package org.osadev.osa.simapis.modeling;

import org.osadev.osa.simapis.exceptions.IllegalEventMethodException;
import org.osadev.osa.simapis.exceptions.UnknownEventMethodException;
import org.osadev.osa.simapis.simulation.SimulationEventItf;

/**
 * 
 * @author odalle
 *
 */
public interface EventModelingAPI<U extends Comparable<U>> extends SimulationTimeAPI<U> {

	/**
	   * Schedule a new method call at a given time.
	   * 
	   * <p>This implements the event-driven model in which an action is scheduled 
	   * for execution at a particular time.
	   * 
	   * <p> Following the pure event-driven model, the event processing does not change the
	   * current simulation time. In other words, in the event-driven model, the processing is
	   * instantaneous regardless of its computing complexity.
	   * 
	   * <p> The schedule action returns an event id that can be used for cancellation using
	   * {@link EventModelingAPI#cancelEvent(long)}.
	   * 
	   * 
	   * @param methodName
	   *        The method that must be called.
	   * @param parameters
	   *        The method's parameters.
	   * @param time
	   *        The simulation time at which the method must be
	   *        executed.
	   * @return
	   * 		Event id
	   * 
	   * @throws UnknownMethodException
	   * 		If the requested method is found in the current object instance by its 
	   * 		class loader.
	   * 
	   * @throws IllegalMethodException
	   * 		If the requested method is found but cannot be used for building an event.
	   *        
	   */
	  long scheduleEventMyself(String methodName, Object[] parameters, ModelingTimeAPI<U> time)
	  throws UnknownEventMethodException, IllegalEventMethodException;
	  
	  
	  /**
	   * Cancel a scheduled event.
	   * 
	   * @param eventId
	   * 		The event id returned by {@link org.osadev.osa.simapis.modeling#scheduleEventMyself(String, Object[], ModelingTimeAPI)}.
	   * 
	   * @return
	   * 		Event if event was found and cancellation was successful, null otherwise.
	   */
	  boolean cancelEvent(long eventId);
	  
	  /**
	   * Get a scheduled event by id.
	   * 
	   * @param eventId
	   * @return
	   * 	Event if event was found, null otherwise.
	   */
	  //E getEvent(long eventId);
	
}
