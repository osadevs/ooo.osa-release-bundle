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
package org.osadev.osa.simapis.newdes.process.spoon;

import org.objectweb.fractal.fraclet.annotations.Requires;
import org.objectweb.fractal.fraclet.types.Contingency;

import spoon.template.Template;
import org.osadev.osa.simapis.modeling.ModelingTimeAPI;
import org.osadev.osa.simapis.simulation.ProcessSuperSchedulerItf;

public class SimulationControllerTemplate implements Template {

  /** The super scheduler. */
  @Requires(name = "superscheduler", contingency = Contingency.OPTIONAL)
  private ProcessSuperSchedulerItf<ModelingTimeAPI<Long>> superScheduler_;

}
