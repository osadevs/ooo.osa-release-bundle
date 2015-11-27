/** ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--> 
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
 * Simulation events. Each event is formalized by a method call at a given
 * simulation time. The event also keeps track of the instance of the simulation
 * component to which the method belongs to.
 * 
 */
public abstract class AbstractEvent<U extends Comparable<U>> implements SimulationEventItf<U>, IndexableAndComparable<Long,AbstractEvent<U>> {

	

	/** The method_ to call. */
	private final String method_;

	/** The parameters of the method_. */
	private final Object[] methodParameters_;

	/** The event simulation time_. */
	private final ModelingTimeAPI<U> time_;

	private final Object instance_;
	
	private final Long eventId_;
	
	private static Long eventSequenceId_ = 1L;

	
	protected AbstractEvent(final String method, final Object[] params, final ModelingTimeAPI<U> time, final Object instance) {
		method_ = method;
		methodParameters_ = params;
		time_ = time;
		instance_ = instance;
		synchronized(AbstractEvent.class) {
			eventSequenceId_ = eventId_ = eventSequenceId_+1L;
		}
	}
	
	public static <E extends AbstractEvent<?>> E createEvent() {
		return E.createEvent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.osa.simapis.basic.SimulationEventItf#invoke()
	 */
	public void invoke() throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		try {
			// Simple case: an exact match is found
			getEvtInstance().getClass()
					.getMethod(getEvtMethod(), getEvtArgTypes(false))
					.invoke(getEvtInstance(), getEvtParam());
		} catch (NoSuchMethodException nse) {
			// tricky case: attempt a relaxed match with parameters having Object type.
			// This is needed to cover the complex cases where parameter matching fails
			// (eg abstract class where parameter type is not known). In that case
			// we assume the callback method is defined with object type param(s)
			getEvtInstance().getClass()
					.getMethod(getEvtMethod(), getEvtArgTypes(true))
					.invoke(getEvtInstance(), getEvtParam());
		}
	}

	protected Class<?>[] getEvtArgTypes(boolean try_object) {
		Object[] parameters = getEvtParam();
		Class<?>[] argtypes;
		if (parameters == null) {
			argtypes = null;
		} else {
			argtypes = new Class<?>[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				if (try_object) {
					argtypes[i] = Object.class; 
				} else {
					argtypes[i] = parameters[i].getClass();
				}
			}
		}
		return argtypes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.osa.simapis.basic.SimulationEventItf#getEvtMethod()
	 */
	public final String getEvtMethod() {
		return method_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.osa.simapis.basic.SimulationEventItf#getEvtParam()
	 */
	public final Object[] getEvtParam() {
		return methodParameters_;
	}


	/*
	 * (non-Javadoc)
	 * @see fr.inria.osa.simapis.newdes.simulation.SimulationEventItf#getTime()
	 */
	public final ModelingTimeAPI<U> getTime() {
		return time_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.inria.osa.simapis.basic.SimulationEventItf#getEvtInstance()
	 */
	public Object getEvtInstance() {
		return this.instance_;
	}
	

	public int compareTo(AbstractEvent<U> o) {
		ModelingTimeAPI<U> t = o.getTime();
		return time_.compareTo(t);
	}

	public Long getEventId() {
		return eventId_;
	}
	
	public Long getIndex() {
		return eventId_;
	}

	/**
	 * Give the <code>String</code> representation of this event.
	 * 
	 * @return The <code>String</code> representation of this event.
	 */
	@Override
	public final String toString() {
		return String.format("EV[id=%d, %s(%d params) at t=%d]", eventId_, method_, methodParameters_.length,time_.get());
	}

}
