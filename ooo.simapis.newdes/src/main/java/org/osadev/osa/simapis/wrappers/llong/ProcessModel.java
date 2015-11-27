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
import org.osadev.osa.simapis.modeling.AbstractProcessModel;
import org.osadev.osa.simapis.modeling.ModelingTimeAPI;
import org.osadev.osa.simapis.simulation.SimulationLoggerItf;

/**
 * A wrapper implementation of the generic AbstractProcessModel<U> using the
 * Long type.
 * 
 * <p>
 * The only purpose of this wrapper is to make the code more compact without
 * impact much of its genericity: Multiple versions of this class can be
 * provided for various types in separate java packages.
 * 
 * @author odalle
 *
 */
public class ProcessModel extends AbstractProcessModel<Long> {

    public ProcessModel(SimulationLoggerItf<Long> logger) {
        super(logger);
    }

    public ModelingTimeAPI<Long> getSimulationTime() {
        return this.getSimProcessApi().getSimulationTime();
    }

    public long scheduleEventMyself(String methodName, Object[] parameters,
            ModelingTimeAPI<Long> time) throws UnknownEventMethodException,
            IllegalEventMethodException {
        return this.getSimProcessApi().scheduleEventMyself(methodName,
                parameters, time);
    }

    public long scheduleEventMyselfNoE(String methodName, Object[] parameters,
            ModelingTimeAPI<Long> time) {
        try {
            return this.getSimProcessApi().scheduleEventMyself(methodName,
                    parameters, time);
        } catch (IllegalEventMethodException e) {
            logger_.error("Illgal event method", e);
            throw new RuntimeException(e);
        } catch (UnknownEventMethodException e) {
            logger_.error("Unknown event method", e);
            throw new RuntimeException(e);
        }
    }

    public void waitForDelay(ModelingTimeAPI<Long> delay) {
        this.getSimProcessApi().waitForDelay(delay);
    }

    public String waitOnConditionForDelay(String condition,
            ModelingTimeAPI<Long> delay) throws InterruptedException {
        return this.getSimProcessApi()
                .waitOnConditionForDelay(condition, delay);
    }

    public String waitOnConditionForDelayNoE(String condition,
            ModelingTimeAPI<Long> delay) {
        try {
            return this.getSimProcessApi().waitOnConditionForDelay(condition,
                    delay);
        } catch (InterruptedException e) {
            logger_.error("Interrupted Exception", e);
            throw new RuntimeException(e);
        }
    }

    public boolean cancelEvent(long eventId) {
        return this.getSimProcessApi().cancelEvent(eventId);
    }

    public boolean releaseOneOnCondition(String condition, String param) {
        return this.getSimProcessApi().releaseOneOnCondition(condition, param);
    }

    public int releaseAllOnCondition(String condition, String param) {
        return this.getSimProcessApi().releaseAllOnCondition(condition, param);
    }

    public long scheduleProcessMyself(String methodName, Object[] parameters,
            ModelingTimeAPI<Long> time) throws UnknownEventMethodException,
            IllegalEventMethodException {
        return this.getSimProcessApi().scheduleProcessMyself(methodName,
                parameters, time);
    }

    public long scheduleProcessMyselfNoE(String methodName,
            Object[] parameters, ModelingTimeAPI<Long> time) {
        try {
            return this.getSimProcessApi().scheduleProcessMyself(methodName,
                    parameters, time);
        } catch (IllegalEventMethodException e) {
            logger_.error("Illgal event method", e);
            throw new RuntimeException(e);
        } catch (UnknownEventMethodException e) {
            logger_.error("Unknown event method", e);
            throw new RuntimeException(e);
        }
    }

}
