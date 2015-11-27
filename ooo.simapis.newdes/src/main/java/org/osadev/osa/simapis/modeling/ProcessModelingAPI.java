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

import org.osadev.osa.simapis.exceptions.IllegalEventMethodException;
import org.osadev.osa.simapis.exceptions.UnknownEventMethodException;


/**
 * Provides a basic API for programming Process-based Discrete Event models.
 * 
 * This API covers the basic needs for building a process-based model:
 * <ul>
 * <li>Get the current simulation time</li>
 * <li>Wait for a fixed duration</li>
 * <li>Wait until a condition is met</li>
 * <li>Release threads waiting on a condition</li>
 * </ul>
 * 
 * @author odalle
 *
 */
public interface ProcessModelingAPI<U extends Comparable<U>> extends EventModelingAPI<U> {

  /**
   * Sleep for a given (fixed) amount of time.
   * 
   * <p>Puts the current simulation thread to sleep until the requested time
   * has elapsed. This is the same as waiting on a hidden condition.
   * 
   * <p>This method can only be used with the process-based model.
   * 
   * @param delay
   *		The duration of sleep.
   */
  void waitForDelay(ModelingTimeAPI<U> delay);

  
  /**
   * Sleep until a condition is met or a given delay has elapsed.
   *
   * <p>This primitive implements the receiver side of a CSP communication channel with
   * a time guard. The choice of a CSP channel approach rather than an actor approach is motivated
   * by the fact that CSP does not need to know about the identities of the other communicating
   * side, which is more compliant with our component-based approach.
   * 
   * <p> Put the current thread to sleep until either a condition is met or a given delay has
   * elapsed. The condition is met when another thread uses one of 
   * {@link ProcessModelingAPI#releaseOneOnCondition(String, String)} or 
   * {@link ProcessModelingAPI#releaseAllOnCondition(String, String)} with a condition parameter
   * that verifies equality with the condition parameter used for wait. The condition parameter
   * can this be considered as the name of the CSP channel.
   *
   * <p>If the delay parameter given is negative then the wait blocks until the condition
   * is met without any time limit. Otherwise, if delay is >=0 a thread may return from 
   * wait either because it was woken up by another thread or because the given delay has elapsed.
   * 
   * <p>If the thread is woken up by another thread, the other thread may pass a String message
   * that will be returned by the call to wait. This way, the wait mechanism can be used as a 
   * one way communication channel between two threads.  
   * 
   * <p>If the thread wakes up because the delay has expired, the call to wait returns 
   * the reserved String {@link org.osadev.osa.simapis.simulation.WaitRequest#TIMEDOUT} 
   * 
   * @param condition
   * 		A String object used as a wait condition. The condition is matched if an object that 
   * 		satisfies equality is used as a parameter in a call to release.
   * @param delay
   * 		A maximum waiting delay 
   * @return
   * 		The object given as a second parameter to release or {@literal null} if delay 
   * 		expires.
   * @throws InterruptedException
   */
  public String waitOnConditionForDelay(final String condition, ModelingTimeAPI<U> delay) throws InterruptedException;
  
  /**
   * Wakes up the first thread found waiting on a condition.
   * 
   * <p>The wait/release implementation MUST guarantee that if a matching release is called at 
   * the same time as the wait using the same condition and they are not causally related, 
   * then release will succeed to wake up one thread even if it is not yet asleep. This implies 
   * that release instructions are processed after all other pending events for the current
   * simulation time.
   * 
   * <p>Thread are assumed to be waiting in FIFO order with respect to the simulation time
   * chronology. However if two threads go to sleep using the same condition and at the same
   * simulation time, their wake up order is unspecified unless they have a direct causal
   * relationship. 
   * 
   * 
   * @param condition
   * 		An object used to match the waiting condition for equality
   * @param param
   * 		A parameter the woken-up thread will receive as a return value to its call to wait. 
   * @return
   * 		True if a thread was woken-up.
   *  
   */
   boolean releaseOneOnCondition(String condition, String param);
  
  /**
   * Wakes up all threads found waiting on a condition.
   * 
   * <p>The wait/release implementation MUST guarantee that if a matching release is called at 
   * the same time as the waits using the same condition and they are not causally related, 
   * then release will succeed to wake up all threads even if they are not all yet asleep. 
   * This implies that release instructions are processed after all other pending events 
   * for the current simulation time.
   * 
   * <p>Thread are assumed to be waiting in FIFO order with respect to the simulation time
   * chronology. However if two threads go to sleep using the same condition and at the same
   * simulation time, their wake up order is unspecified unless they have a direct causal
   * relationship. 
   * 
   * 
   * 
   * @param condition
   * 		An object used to match the waiting condition for equality
   * @param param
   * 		A parameter the woken-up thread will receive as a return value to its call to wait. 
   * @return
   * 		The number of woken-up threads.
   *  
   */
  int releaseAllOnCondition(String condition, String param);


/**
   * Schedule a new process to start at a given time.
   * 
   * <p>This implements the process-oriented model in which a process can be started and
   * extend its execution over an arbitrary long duration, alternating periods of inactivity
   * during which the time flows and periods of activity that occur at discrete point of
   * the simulation timeline.
   * 
   * <p> The schedule action returns an event id that can be used for cancellation using
   * {@link EventModelingAPI#cancelEvent(long)}.
   * 
   * 
   * @param methodName
   *        The method that must be called.
   * @param parameters
   *        The method's parameters.
   * @param time
   *        The simulation time at which the method must be
   *        executed.
   * @return
   * 		Event id
   * 
   * @throws UnknownMethodException
   * 		If the requested method is found in the current object instance by its 
   * 		class loader.
   * 
   * @throws IllegalMethodException
   * 		If the requested method is found but cannot be used for building an event.
   *        
   */
  long scheduleProcessMyself(String methodName, Object[] parameters, ModelingTimeAPI<U> time)
  throws UnknownEventMethodException, IllegalEventMethodException;
}
