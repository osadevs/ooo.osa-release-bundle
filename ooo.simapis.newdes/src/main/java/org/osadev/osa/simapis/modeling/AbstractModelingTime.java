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
package org.osadev.osa.simapis.modeling;


public abstract class AbstractModelingTime<U extends Comparable<U>> implements ModelingTimeAPI<U> {

	private U time_;
	
	public boolean isBefore(ModelingTimeAPI<U> other) {
		return (time_.compareTo(other.get()) < 0);
	}

	public boolean isAfter(ModelingTimeAPI<U> other) {
		return (time_.compareTo(other.get()) > 0);
	}
	
	protected AbstractModelingTime(final U t) {
		this.time_ = t;
	}

	public int compareTo(ModelingTimeAPI<U> o) {
		if (isInfinite() && o.isInfinite()) return 0;
		if (isInfinite()) return 1;
		if (o.isInfinite()) return -1;
		return time_.compareTo(o.get());
	}

	public abstract boolean isInfinite();

	public abstract ModelingTimeAPI<U> getDelayed(U t);
	
	public U get() {
		return this.time_;
	}
	
	public String toString() {
		return time_.toString();
	}

}
