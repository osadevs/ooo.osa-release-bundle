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

import org.objectweb.fractal.api.Component;
import org.osadev.osa.simapis.exceptions.OsaRuntimeException;


/**
 * This interface allows a super scheduler component to deploy itself as a 
 * shared component in the component hierarchy and binds itself to all the 
 * simulation components.
 * 
 * @author odalle
 *
 */
public interface SharedSuperSchedulerItf {
	
	/**
	 * Requests the component to copy itself recursively in the hierarchy
	 * and bind to all simulation components found at each level.
	 * 
	 * @param superScheduler
	 * 			The super scheduler component to deploy and bind.
	 * @param rootComponent
	 * 			The target component in which to deploy the superscheduler.
	 * 			
	 */
	public void deployAndBind(Component superScheduler, Component rootComponent) 
	throws OsaRuntimeException;

}
