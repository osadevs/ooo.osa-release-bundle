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

package org.osadev.osa.simapis.wrappers.llong;

import org.osadev.osa.simapis.exceptions.IllegalEventMethodException;
import org.osadev.osa.simapis.exceptions.UnknownEventMethodException;
import org.osadev.osa.simapis.modeling.AbstractEventModel;
import org.osadev.osa.simapis.modeling.ModelingTimeAPI;
import org.osadev.osa.simapis.simulation.SimulationLoggerItf;

/**
 * A wrapper implementation of the generic AbstractEventModel<U> using the Long
 * type.
 * 
 * <p>
 * The only purpose of this wrapper is to make the code more compact without
 * impact much of its genericity: Multiple versions of this class can be
 * provided for various types in separate java packages.
 * 
 * @author odalle
 *
 */
public class EventModel extends AbstractEventModel<Long> {

    public EventModel(final SimulationLoggerItf<Long> logger) {
        super(logger);
    }

    /**
     * Delegate method for
     * {@link org.osadev.osa.simapis.modeling.EventModelingAPI#getSimulationTime()}
     * .
     * 
     * 
     * @see org.osadev.osa.simapis.modeling.EventModelingAPI#getSimulationTime()
     */
    public ModelingTimeAPI<Long> getSimulationTime() {
        return this.getSimEventApi().getSimulationTime();
    }

    /**
     * Delegate method for
     * {@link org.osadev.osa.simapis.modeling.EventModelingAPI#scheduleEventMyself(String, Object[], ModelingTimeAPI)}
     * 
     * @see org.osadev.osa.simapis.modeling.EventModelingAPI#scheduleEventMyself(String,
     *      Object[], ModelingTimeAPI)
     */
    public long scheduleEventMyself(String methodName, Object[] parameters,
            ModelingTimeAPI<Long> time) throws UnknownEventMethodException,
            IllegalEventMethodException {
        return this.getSimEventApi().scheduleEventMyself(methodName,
                parameters, time);
    }

    /**
     * Delegate method for
     * {@link org.osadev.osa.simapis.modeling.EventModelingAPI#scheduleEventMyself(String, Object[], ModelingTimeAPI)}
     * 
     * <p>
     * No Exception version: exceptions are trapped and handled in this wrapper.
     * 
     * @see org.osadev.osa.simapis.modeling.EventModelingAPI#scheduleEventMyself(String,
     *      Object[], ModelingTimeAPI)
     */
    public long scheduleEventMyselfNoE(String methodName, Object[] parameters,
            ModelingTimeAPI<Long> time) {
        try {
            return this.getSimEventApi().scheduleEventMyself(methodName,
                    parameters, time);
        } catch (IllegalEventMethodException e) {
            logger_.error("Illgal event method", e);
            throw new RuntimeException(e);
        } catch (UnknownEventMethodException e) {
            logger_.error("Unknown event method", e);
            throw new RuntimeException(e);
        }
    }

    public boolean cancelEvent(long eventId) {
        return this.getSimEventApi().cancelEvent(eventId);
    }

}
