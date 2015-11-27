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
package org.osadev.osa.simapis.exceptions;

/**
 * Exception resulting from an internal/run-time error or situation.
 *
 * <p> This exception is used or extended for reporting any situation
 * originating in the OSA runtime: configuration, missing parameter, 
 * execution error and so on.
 * 
 * @author odalle
 *
 */
public class OsaRuntimeException extends OsaException {
	private static final String EXCEPTION_DESC = "Osa Runtime Exception";
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 5260170250793964101L;
	

	public OsaRuntimeException() {
		super(EXCEPTION_DESC);
	}

	public OsaRuntimeException(String arg0) {
		super(EXCEPTION_DESC + arg0);
	}

	public OsaRuntimeException(Throwable arg0) {
		super(EXCEPTION_DESC, arg0);
	}

	public OsaRuntimeException(String arg0, Throwable arg1) {
		super(EXCEPTION_DESC + arg0, arg1);
	}

}
