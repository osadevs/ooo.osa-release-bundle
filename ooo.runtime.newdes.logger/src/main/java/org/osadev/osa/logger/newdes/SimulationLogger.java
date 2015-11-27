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
package org.osadev.osa.logger.newdes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import org.osadev.osa.simapis.simulation.SimulationLoggerItf;
import org.osadev.osa.simapis.modeling.SimulationTimeAPI;

/**
 * An extended logger based on slf4j that automatically prepends the current
 * simulation time to all traces.
 * 
 * @author odalle
 *
 * @param <U>
 *            The generic type used for holding simulation time.
 */
public class SimulationLogger<U extends Comparable<U>> implements
        SimulationLoggerItf<U> {

    final private Logger         logger_;
    private SimulationTimeAPI<U> timeAPI_ = null;

    private String               timeStr_ = null;
    private U                    timeVal_ = null;

    public SimulationLogger(final Class<?> clazz) {
        logger_ = LoggerFactory.getLogger(clazz);
    }

    /**
     * Get curent simulation time from simulation engine.
     * 
     * @return
     *          current simulation time
     */
    private final String getTimeString() {
        if (timeAPI_ == null)
            return null;
        U current = timeAPI_.getSimulationTime().get();
        if (current != timeVal_) {
            timeVal_ = current;
            timeStr_ = current.toString() + ": ";
        }
        return timeStr_;
    }

    /**
     * Prepand current simulation time to a message
     * 
     * @param message
     *          Message to which time is to be prepanded
     * @return
     *          Updated message
     */
    private final String prepandTime(final String message) {
        String str = getTimeString();
        if (str != null)
            return str + message;
        else
            return message;
    }

    /*
     * (non-Javadoc)
     * @see org.osadev.osa.simapis.simulation.TimeApiSetterItf#setTimeApi(org.osadev.osa.simapis.modeling.SimulationTimeAPI)
     */
    public void setTimeApi(SimulationTimeAPI<U> timeAPI) {
        this.timeAPI_ = timeAPI;
    }

    /**
     * {@inheritDoc}
     */
    public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
        logger_.debug(arg0, prepandTime(arg1), arg2, arg3);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(Marker arg0, String arg1, Object... arg2) {
        logger_.debug(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(Marker arg0, String arg1, Object arg2) {
        logger_.debug(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(Marker arg0, String arg1, Throwable arg2) {
        logger_.debug(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(Marker arg0, String arg1) {
        logger_.debug(arg0, prepandTime(arg1));
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String arg0, Object arg1, Object arg2) {
        logger_.debug(prepandTime(arg0), arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String arg0, Object... arg1) {
        logger_.debug(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String arg0, Object arg1) {
        logger_.debug(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String arg0, Throwable arg1) {
        logger_.debug(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String arg0) {
        logger_.debug(prepandTime(arg0));
    }

    /**
     * {@inheritDoc}
     */
    public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
        logger_.error(arg0, prepandTime(arg1), arg2, arg3);
    }

    /**
     * {@inheritDoc}
     */
    public void error(Marker arg0, String arg1, Object... arg2) {
        logger_.error(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void error(Marker arg0, String arg1, Object arg2) {
        logger_.error(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void error(Marker arg0, String arg1, Throwable arg2) {
        logger_.error(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void error(Marker arg0, String arg1) {
        logger_.error(arg0, prepandTime(arg1));
    }

    /**
     * {@inheritDoc}
     */
    public void error(String arg0, Object arg1, Object arg2) {
        logger_.error(prepandTime(arg0), arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String arg0, Object... arg1) {
        logger_.error(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String arg0, Object arg1) {
        logger_.error(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String arg0, Throwable arg1) {
        logger_.error(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String arg0) {
        logger_.error(prepandTime(arg0));
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return logger_.getName();
    }

    /**
     * {@inheritDoc}
     */
    public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
        logger_.info(arg0, prepandTime(arg1), arg2, arg3);
    }

    /**
     * {@inheritDoc}
     */
    public void info(Marker arg0, String arg1, Object... arg2) {
        logger_.info(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void info(Marker arg0, String arg1, Object arg2) {
        logger_.info(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void info(Marker arg0, String arg1, Throwable arg2) {
        logger_.info(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void info(Marker arg0, String arg1) {
        logger_.info(arg0, prepandTime(arg1));
    }

    /**
     * {@inheritDoc}
     */
    public void info(String arg0, Object arg1, Object arg2) {
        logger_.info(prepandTime(arg0), arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String arg0, Object... arg1) {
        logger_.info(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String arg0, Object arg1) {
        logger_.info(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String arg0, Throwable arg1) {
        logger_.info(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void info(String arg0) {
        logger_.info(prepandTime(arg0));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDebugEnabled() {
        return logger_.isDebugEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDebugEnabled(Marker arg0) {
        return logger_.isDebugEnabled(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isErrorEnabled() {
        return logger_.isErrorEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isErrorEnabled(Marker arg0) {
        return logger_.isErrorEnabled(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInfoEnabled() {
        return logger_.isInfoEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInfoEnabled(Marker arg0) {
        return logger_.isInfoEnabled(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTraceEnabled() {
        return logger_.isTraceEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTraceEnabled(Marker arg0) {
        return logger_.isTraceEnabled(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isWarnEnabled() {
        return logger_.isWarnEnabled();
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isWarnEnabled(Marker arg0) {
        return logger_.isWarnEnabled(arg0);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
        logger_.trace(arg0, prepandTime(arg1), arg2, arg3);
    }
    
    /**
     * {@inheritDoc}
     */
    public void trace(Marker arg0, String arg1, Object... arg2) {
        logger_.trace(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(Marker arg0, String arg1, Object arg2) {
        logger_.trace(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(Marker arg0, String arg1, Throwable arg2) {
        logger_.trace(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(Marker arg0, String arg1) {
        logger_.trace(arg0, prepandTime(arg1));
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String arg0, Object arg1, Object arg2) {
        logger_.trace(prepandTime(arg0), arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String arg0, Object... arg1) {
        logger_.trace(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String arg0, Object arg1) {
        logger_.trace(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String arg0, Throwable arg1) {
        logger_.trace(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void trace(String arg0) {
        logger_.trace(prepandTime(arg0));
    }

    /**
     * {@inheritDoc}
     */
    public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
        logger_.warn(arg0, prepandTime(arg1), arg2, arg3);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(Marker arg0, String arg1, Object... arg2) {
        logger_.warn(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(Marker arg0, String arg1, Object arg2) {
        logger_.warn(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(Marker arg0, String arg1, Throwable arg2) {
        logger_.warn(arg0, prepandTime(arg1), arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(Marker arg0, String arg1) {
        logger_.warn(arg0, prepandTime(arg1));
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String arg0, Object arg1, Object arg2) {
        logger_.warn(prepandTime(arg0), arg1, arg2);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String arg0, Object... arg1) {
        logger_.warn(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String arg0, Object arg1) {
        logger_.warn(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String arg0, Throwable arg1) {
        logger_.warn(prepandTime(arg0), arg1);
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String arg0) {
        logger_.warn(prepandTime(arg0));
    }

}
