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

import java.lang.reflect.InvocationTargetException;

import org.osadev.osa.simapis.modeling.ModelingTimeAPI;

/**
 * 
 * Defines the API for OSA events.
 * 
 * <p>
 * OSA events are similar to functor objects, that is an object that contains a
 * method to be executed along with its arguments, and the virtual simulation
 * time at which to schedule the execution. However, to be consistent with the
 * encapsulation principles of Fractal, events can only be created and executed
 * within the component that owns the (the class that owns the) method scheduled
 * by the event. Because of this limitation the OSA event API is mostly internal
 * because events are created internally by the simulation controller of the
 * component that owns the method scheduled in the event.
 * 
 * @author odalle
 *
 */
public interface SimulationEventItf<U extends Comparable<U>> 
//extends IndexableAndComparable<Long,T> 
{

	/**
	 * Give the event processed currentTime_ (simulation currentTime_).
	 * 
	 * @return The event processed currentTime_.
	 */
	public ModelingTimeAPI<U> getTime();

	/**
	 * Executes the method designated by the event using the parameters of the
	 * event.
	 * 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void invoke() throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	/**
	 * Give the method associated with this event.
	 * 
	 * @return The method called when this event is processed.
	 */
	public String getEvtMethod();

	/**
	 * Give the parameters of event method.
	 * 
	 * @return The array of event method parameters.
	 */
	public Object[] getEvtParam();


	/**
	 * Get the event object instance.
	 * 
	 * @return the event object instance.
	 */
	public Object getEvtInstance();
	
	/**
	 * Returns a unique event identifier.
	 * 
	 * @return event ID.
	 */
	public Long getEventId(); 

}
