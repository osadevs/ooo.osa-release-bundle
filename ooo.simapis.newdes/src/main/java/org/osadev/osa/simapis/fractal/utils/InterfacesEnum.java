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
package org.osadev.osa.simapis.fractal.utils;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.NoSuchInterfaceException;


/**
 * Enumerates the component interfaces to avoid misspelling errors.
 * 
 * @author odalle
 *
 */
public enum InterfacesEnum {
	// List Fractal commonly used interfaces
	FC_COMPONENT ("component"),
	FC_BINDING_CONTROLLER ("binding-controller"),
	
	// List OSA specific interfaces
	/** 
	 * Simulation controller interface.
	 * 
	 * <P> Exposed by model component to provide scheduling control to super-scheduler.
	 **/
	OSA_SIMULATION_CONTROLLER 			("simulation-controller"),
	/** 
	 * Event Modeling API
	 * 
	 * <p> Exposed by model components to model content part to provide
	 * modeling services following the event-driven approach.
	 **/
	OSA_MODELING_EVENT_CONTROLLER 		("modeling-event-controller"),
	/** 
	 * Process Modeling API
	 * 
	 * <p> Exposed by model components to model content part to provide
	 * modeling services following the process-oriented approach.
	 **/
	OSA_MODELING_PROCESS_CONTROLLER 	("modeling-process-controller"),
	/**
	 * Super scheduler control interface
	 * 
	 * <p>Used for controlling the simulation execution and setup.
	 */
	OSA_SUPER_SCHEDULER_CONTROL			("superschedulercontrol"),
	/**
	 * Super scheduler services
	 * 
	 * <p>Exposed by super-scheduler to model component to provide
	 * top level scheduling services and coordination.
	 */
	OSA_SUPER_SCHEDULER_SRV_ITF		("superschedulersvc"),
	/**
	 * Super scheduler services (client side)
	 * 
	 * <p>Internal super-scheduler interface accessed with simulation
	 * models.
	 */
	OSA_SUPER_SCHEDULER_CLI_ITF		("superscheduler");
	
	private final String itfName_;
	
	public final Object getInterface(final Component comp, String errMsg) {
		try {
			return comp.getFcInterface(itfName_);
		} catch (NoSuchInterfaceException e) {
			throw new RuntimeException(
					String.format("%s: Failed to retrieve interface %s on component %s: ",
							errMsg, itfName_, comp.toString()),e);
		}
	}
	
	public final Object getInterface(final Component comp) throws NoSuchInterfaceException {
		return comp.getFcInterface(itfName_);
	}
	
	public final String getItfName() {
		return itfName_;
	}
	
	InterfacesEnum(final String itfName) {
		itfName_ = itfName;
	}
}
