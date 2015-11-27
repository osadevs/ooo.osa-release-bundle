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

/**
 * Provides elementary integer arithmetics on time units.
 * 
 * Although this enum class is very similar to {@link java.util.concurrent.TimeUnit}
 * it does NOT extend it to avoid mixing simulation synchronization with the default
 * java synchronization. Compared to the default java enum, this enum offers more
 * units (downto to femto-secs) and comes with a default unit. It does NOT offer
 * synchronization service. 
 * 
 * <p><strong>Warning</strong>: no loss of precision checks are done.
 * RULE OF THUMB: never convert from higher to lower precision eg.
 * from nano-seconds to seconds.
 * 
 * <p>Examples:
 * <ul>
 * <li>Convert 18 milli-seconds to internal representation:<br>
 * <tt>TimeUnit.MILLI_SECONDS.toIntern(18)</tt>
 * 
 * <li>Print 18 milliseconds using standard abbeviations:<br>
 * <tt>TimeUnit.MILLI_SECONDS.format(18)</tt>
 * 
 * <li>Convert 3 hours into milli-seconds:<br>
 * <tt>TimeUnit.MILLI_SECONDS.fromUnit(3,TimeUnit.HOURS)</tt><br>
 * OR<br>
 * <tt>TimeUnit.HOURS.toUnit(3,TimeUnit.MILLI_SECONDS)</tt>
 * 
 * <li>Add 2 days and 3 hour to current_time (already in internal rep):<br>
 * <tt>current_time += TimeUnit.DAYS.toIntern(2) + TimeUnit.HOURS.toIntern(3);</tt><br>
 * => current_time + 183 600 000 000 000 000
 *  
 * <li>Example of loss of precision (attempts to convert 100ms to seconds):<br>
 * <tt>TimeUnit.MICRO_SECONDS.toUnit(100, TimeUnit.SECONDS)</tt><br>
 * => 0
 * </ul>
 * 
 * @author odalle
 *
 */
public enum TimeUnit {
	FEMTO_SECONDS("fs", 1L, 1000L * 1000L * 1000L *1000L * 1000L), // Warning: MAX approx 1000 seconds
	PICO_SECONDS ("ps", 1L, 1000L * 1000L * 1000L *1000L),
	NANO_SECONDS ("ns", 1L, 1000000000L),
	MICRO_SECONDS ("us", 1L, 1000000L),
	MILLI_SECONDS ("ms", 1L, 1000L),
	SECONDS ("s", 1L, 1L),
	MINUTES ("min", 60L, 1L),
	HOURS ("hr", 3600L, 1L),
	DAYS ("d", 3600L * 24L, 1L);
	
	private final char[] abbrev_;
	private final long numerator_;
	private final long denominator_;
	
	/**
	 * WARNING: This is set once for all.
	 */
	public static final TimeUnit INTERN_UNIT = PICO_SECONDS;
	
	TimeUnit(String abbrev, long num, long den) {
		abbrev_= abbrev.toCharArray();
		numerator_ = num;
		denominator_ = den;
	}
	
	
	private TimeUnit(String abbrev) {
		abbrev_=abbrev.toCharArray();;
		numerator_=1;
		denominator_=1;
	}
	
	public long toUnit(long amount, TimeUnit unit) {
		return (amount * numerator_ * unit.denominator_ ) / (denominator_ * unit.numerator_);
	}
	
	public long fromUnit(long amount, TimeUnit unit) {
		return (amount * denominator_ *  unit.numerator_) / (numerator_ * unit.denominator_);
	}
	
	public long toIntern(long amount) {
		return toUnit(amount, INTERN_UNIT);
	}
	
	public long fromIntern(long amount) {
		return fromUnit(amount, INTERN_UNIT);
	}
	
	public char[] getAbbrev(){
		return abbrev_;
	}

	public long getNumerator() {
		return numerator_;
	}
	
	public long getDenominator() {
		return denominator_;
	}
	
	/**
	 * Parse a time formatted string.
	 * 
	 * <p> The time format follows the same model as the one produced by the 
	 * {@link TimeUnit#format(long, boolean)} function.
	 * More precisely it is able to recognize a long integer possibly followed 
	 * by a time unit abbreviation:<br>
	 * <tt>llll[ uu]</tt><br> 
	 * where {@literal llll} is a long integer and {@literal uu} is the time unit abbrevation.
	 * 
	 * <p>In case the time unit is missing, the default (internal) unit is used.
	 * 
	 * @param time
	 * 			The string to be parsed
	 * @return
	 * 			The time value expressed using the default (internal) unit.
	 * @throws
	 * 			NumberFormatException if the parsing fails.		
	 * 
	 */
	public static long parseTime(String time) throws NumberFormatException {
		String parts[] = time.split(" ");
		long value = Long.parseLong(parts[0]);
		if (parts.length != 1) {
			for (TimeUnit t: TimeUnit.values()) {
				if (t.abbrev_.equals(parts[1]))
					value = t.toIntern(value);
			}
		}
		return value;
	}
	
	public String format(long amount, boolean spaced){
		return "" + amount + (spaced ? " " : "") + toString();
	}
	
	public String toString(){
		return abbrev_.toString();
	}
}
