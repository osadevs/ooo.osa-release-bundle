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

import org.objectweb.fractal.api.NoSuchInterfaceException;
import org.objectweb.fractal.fraclet.annotations.Component;
import org.objectweb.fractal.fraclet.annotations.Controller;
import org.objectweb.fractal.fraclet.extensions.Membrane;
import org.osadev.osa.simapis.exceptions.IllegalEventMethodException;
import org.osadev.osa.simapis.exceptions.UnknownEventMethodException;
import org.osadev.osa.simapis.simulation.SimulationLoggerItf;

@Component
@Membrane(controller = "simPrimitive")
public class AbstractProcessModel<U extends Comparable<U>> {
	
	public final SimulationLoggerItf<U> logger_;
	
	public AbstractProcessModel(SimulationLoggerItf<U> logger){
		logger_ = logger;
	}
	
	/** The simulation controller. */
	@Controller("modeling-process-controller")
	private ProcessModelingAPI<U> simProcessApi_;
	
	protected ProcessModelingAPI<U> getSimProcessApi(){
		return simProcessApi_;
	}
	
	protected ModelingTimeAPI<U> getSimTime() {
		return simProcessApi_.getSimulationTime();
	}
	
	
	protected long scheduleEventMyself(String methodName, Object[] parameters,
			ModelingTimeAPI<U> time) throws UnknownEventMethodException,
			IllegalEventMethodException {
		return simProcessApi_.scheduleEventMyself(methodName, parameters, time);
	}

	protected void waitForDelay(ModelingTimeAPI<U> delay) {
		simProcessApi_.waitForDelay(delay);
	}

	protected String waitOnConditionForDelay(String condition,
			ModelingTimeAPI<U> delay) throws InterruptedException {
		return simProcessApi_.waitOnConditionForDelay(condition, delay);
	}

	protected boolean cancelEvent(long eventId) {
		return simProcessApi_.cancelEvent(eventId);
	}

	protected boolean releaseOneOnCondition(String condition, String param) {
		return simProcessApi_.releaseOneOnCondition(condition, param);
	}

	protected int releaseAllOnCondition(String condition, String param) {
		return simProcessApi_.releaseAllOnCondition(condition, param);
	}

	protected long scheduleProcessMyself(String methodName, Object[] parameters,
			ModelingTimeAPI<U> time) throws UnknownEventMethodException,
			IllegalEventMethodException {
		return simProcessApi_.scheduleProcessMyself(methodName, parameters,
				time);
	}

	public synchronized void bindFc(String id, Object ref) throws NoSuchInterfaceException {
        if (id.equals("component")) {
            this.simProcessApi_ = ((ProcessModelingAPI<U>)(((org.objectweb.fractal.api.Component)(ref)).getFcInterface("modeling-process-controller")));
            logger_.setTimeApi(this.simProcessApi_);
        }
            
        throw new NoSuchInterfaceException((("Client interface \'" + id) + "\' is undefined."));
    }
	
}
