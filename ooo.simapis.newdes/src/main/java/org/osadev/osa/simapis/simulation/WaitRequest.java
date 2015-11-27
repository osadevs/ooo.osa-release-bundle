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

import java.util.concurrent.Semaphore;

/**
 * Simple implementation of the {@link SimulationWaitRequestItf} interface.
 * 
 * @author odalle
 * @see SimulationWaitRequestItf
 */
public class WaitRequest<U extends Comparable<U>> implements
        SimulationWaitRequestItf<U> {

    public static final String    TIMEDOUT   = "TimeOut";
    private final Semaphore       sema_      = new Semaphore(0);
    private String                result_;
    private SimulationEventItf<U> event_;
    private String                condition_ = null;

    public WaitRequest() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.inria.osa.simapis.basic.simulation.SimulationWaitRequestItf#setResult
     * (java.lang.String)
     */
    public synchronized void setResult(final String result) {
        if (result.equals(TIMEDOUT))
            throw new RuntimeException("Illegal result " + TIMEDOUT
                    + " is a reserved keyword");
        result_ = result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.inria.osa.simapis.basic.simulation.SimulationWaitRequestItf#setTimedOut
     * ()
     */
    public synchronized void setTimedOut() {
        result_ = TIMEDOUT;
    }

    public void setCondition(String condition) {
        condition_ = condition;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.inria.osa.simapis.basic.simulation.SimulationWaitRequestItf#getEvent()
     */
    public SimulationEventItf<U> getEvent() {
        return this.event_;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.inria.osa.simapis.basic.simulation.SimulationWaitRequestItf#setEvent
     * (fr.inria.osa.simapis.basic.simulation.SimulationEventItf)
     */
    public void setEvent(final SimulationEventItf<U> event) {
        this.event_ = event;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.inria.osa.simapis.basic.simulation.SimulationWaitRequestItf#isTimedOut
     * ()
     */
    public boolean isTimedOut() {
        return (result_ != null) && result_.equals(TIMEDOUT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.inria.osa.simapis.basic.simulation.SimulationWaitRequestItf#getResult
     * ()
     */
    public synchronized final String getResult() {
        return this.result_;
    }

    public final String getCondition() {
        return this.condition_;
    }

    /**
     * Used to block the simulation thread on a with condition.
     * 
     * <p>
     * This is just a delegate method for
     * {@link java.lang.concurrent.Semaphore#acquire() }
     * 
     * @throws InterruptedException
     * @see java.lang.concurrent.Semaphore#acquire()
     */
    public void acquire() throws InterruptedException {
        this.sema_.acquire();
    }

    /**
     * Used to resume the simulation thread previously blocked on a wait
     * condition.
     * 
     * <p>
     * This is just a delegate method for
     * {@link java.lang.concurrent.Semaphore#releasee() }
     * 
     * @throws InterruptedException
     * @see java.lang.concurrent.Semaphore#release()
     */
    public void release() {
        this.sema_.release();
    }

    public int availablePermits() {
        return sema_.availablePermits();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("WR[res=%s,event=%s,condition=%s]", result_,
                event_, condition_);
    }
}
