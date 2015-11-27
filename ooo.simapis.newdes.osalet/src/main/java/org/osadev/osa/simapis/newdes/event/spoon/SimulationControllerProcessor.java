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
package org.osadev.osa.simapis.newdes.event.spoon;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.osadev.osa.simapis.newdes.event.spoon.SimulationControllerTemplate;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtClass;
import spoon.template.Substitution;
import spoon.template.Template;

/**
 * Inserts the simulation Controller in model classes using a template
 */
public class SimulationControllerProcessor<A extends Annotation>  extends AbstractProcessor<CtAnnotation<A>> {

  /**
   * {@inheritDoc}
   * 
   * <p>The selected elements are those that are annotated with the 
   * <br><center>{@code controller="simPrimitive"}</center> annotation.
   * 
   */
  public boolean isToBeProcessed(CtAnnotation<A> candidate) {
    Map<String,Object> elements = candidate.getElementValues();
    if(elements.containsKey("controller")) {
    	String controller = elements.get("controller").toString();
    	if (controller.equalsIgnoreCase("\"simBasicPrimitive\""))
    		return true;
    }
    return false;
  }

  
  public void process(CtAnnotation<A> access) {
    CtClass<?> ct = access.getParent(CtClass.class);
    Template t = new SimulationControllerTemplate();
    Substitution.insertAll(ct, t);
  }

}
