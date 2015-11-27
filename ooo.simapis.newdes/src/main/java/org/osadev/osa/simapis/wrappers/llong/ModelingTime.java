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

import org.osadev.osa.simapis.modeling.AbstractModelingTime;
import org.osadev.osa.simapis.modeling.ModelingTimeAPI;
import org.osadev.osa.simapis.modeling.ModelingTimeSymbols;
import org.osadev.osa.simapis.modeling.TimeFactoryItf;

/**
 * An specialization of the abstract Modeling Time class using the long type.
 * 
 * @author odalle
 * @see org.osadev.osa.simapis.modeling.AbstractModelingTime
 */
public class ModelingTime extends AbstractModelingTime<Long> {

    /**
     * A factory class to create ModelingTime<Long> object instances.
     * 
     * @author odalle
     */
    public static class ModelingTimeFactory implements TimeFactoryItf<Long> {

        public ModelingTimeAPI<Long> create(Long val) {
            return new ModelingTime(val);
        }

        public ModelingTimeAPI<Long> create() {
            return new ModelingTime(0L);
        }

        public ModelingTimeAPI<Long> create(String symbol) {
            switch (ModelingTimeSymbols.valueOf(symbol)) {
            case INFINITY:
                return new ModelingTime(-1L);
            case ZERO:
                return new ModelingTime(0L);
            default:
                throw new RuntimeException("Invalid Modeling time symbol:"
                        + symbol);

            }
        }

    }

    /**
     * Factory accessor.
     * 
     * @return
     *          ModelingTime factory.
     */
    public static ModelingTimeFactory getFactory() {
        return new ModelingTimeFactory();
    }

    /**
     * Constructor (private).
     * 
     * @param t
     *          A time value
     */
    private ModelingTime(final Long t) {
        super(t);
    }

    @Override
    public boolean isInfinite() {
        return (get() == -1L);
    }

    /**
     * Checks if a time has infinity value.
     * 
     * @param t
     *          Time to check
     * @return
     *          <code>true</code> if time value is infinity.
     */
    public static boolean isInfinite(ModelingTime t) {
        return (t.get() == -1L);
    }


    /**
     * Computes a new date corresponding to the time value in this object delayed by a given amount of time.
     * 
     * @param t
     *          The delay
     * @return
     *          A new instance containing the delayed time.
     */
    @Override
    public ModelingTimeAPI<Long> getDelayed(Long t) {
        return new ModelingTime(get() + t);
    }

    public static ModelingTimeAPI<Long> getDelayed(ModelingTime t1, Long delay) {
        return t1.getDelayed(delay);
    }

}
