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
 * Exception resulting from the use of the OSA API.
 * 
 * This exception is used or derived to report any error or
 * situation related to the use of the OSA API.
 * 
 * @author odalle
 *
 */
public class OsaAPIException extends OsaException {
	private static final String EXCEPTION_DESC = "Osa API Exception";
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 5260170250793964001L;
	
	public OsaAPIException() {
		super(EXCEPTION_DESC);
	}

	public OsaAPIException(String arg0) {
		super(EXCEPTION_DESC + arg0);
	}

	public OsaAPIException(Throwable arg0) {
		super(EXCEPTION_DESC, arg0);
	}

	public OsaAPIException(String arg0, Throwable arg1) {
		super(EXCEPTION_DESC + arg0, arg1);
	}

}
