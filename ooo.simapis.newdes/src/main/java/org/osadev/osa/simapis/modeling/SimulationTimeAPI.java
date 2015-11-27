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

public interface SimulationTimeAPI<U extends Comparable<U>> {

	/**
	 * Give the current simulation time.
	 * 
	 * The values are normalized to 64 bits long integers (long type) and
	 * the internal representation time unit is defined by {@link TimeUnit#INTERN_UNIT}.
	 * 
	 * @return The current simulation time expressed in units of internal time.
	 */
	ModelingTimeAPI<U> getSimulationTime();

}
