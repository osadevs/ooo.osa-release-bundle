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



/**
 * A wait request represents a thread waiting on a condition and
 * getting a result once the wait is complete. The result is either
 * a String received from the releaser, or notification of a timeout.
 * 
 * @author odalle
 *
 */
public interface SimulationWaitRequestItf<U extends Comparable<U>> {
	
	
	/**
	 * Set the result of request. 
	 * 
	 * Used to set the message provided by the releaser (sender) to be delivered
	 * to the waiter (receiver)
	 * @param result
	 * 		The value to return to the waiter
	 */
	public void setResult(final String result);
	
	/**
	 * Set the result of request to "timed out"
	 */
	public void setTimedOut();
	
	/**
	 * Get the timeout event associated to this request
	 * 
	 * @return 
	 *		Reference to time out event
	 */
	public SimulationEventItf<U> getEvent();
	
	/**
	 * Set timeout event.
	 * 
	 * Since the wait on a condition can be guarded by a timeout, the corresponding
	 * timeout ends up being handled like a regular event.
	 * 
	 * @param event
	 * 		Reference to time out event
	 */
	public void setEvent(final SimulationEventItf<U> event);
	
	/**
	 * Check if the wait was timed out
	 * @return
	 * 		True if wait was timed out
	 */
	public boolean isTimedOut();
	
	
	/**
	 * Retrieve the message sent by the releaser (sender) or the reserved timed
	 * out message.
	 * 
	 * The {@link #isTimedOut()} method can be used prior to calling this
	 * method to check whether this message is going to be a timed out message.
	 * 
	 * @return
	 * 		A message
	 */
	public String getResult();

}
