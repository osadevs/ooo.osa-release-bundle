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
import org.osadev.osa.simapis.simulation.SimulationLoggerItf;

@Component
@Membrane(controller = "simBasicPrimitive")
public abstract class AbstractEventModel<U extends Comparable<U>> {
	
	public final SimulationLoggerItf<U> logger_;
	
	public AbstractEventModel(SimulationLoggerItf<U> logger){
		logger_ = logger;
	}
	
	/** The simulation controller. */
	@Controller("modeling-event-controller")
	private EventModelingAPI<U> simEventApi_;
	
	protected EventModelingAPI<U> getSimEventApi(){
		return simEventApi_;
	}
	
	public synchronized void bindFc(String id, Object ref) throws NoSuchInterfaceException {
        if (id.equals("component")) {
            this.simEventApi_ = ((EventModelingAPI<U>)(((org.objectweb.fractal.api.Component)(ref)).getFcInterface("modeling-event-controller")));
            logger_.setTimeApi(this.simEventApi_);
        }
            
        throw new NoSuchInterfaceException((("Client interface \'" + id) + "\' is undefined."));
    }
	
}
