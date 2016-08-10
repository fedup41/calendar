package com.obdobion.calendar;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * ICalendarFactory interface.
 * </p>
 * This class parses phrases that will be used to compute a date. That date will
 * be returned as a Calendar.
 * <p>
 * The first token can be a special date function that is relative to the
 * current date.
 * <ul>
 * <li>Today or Now
 * <li>specific date in a format that is supported by the Date argument.
 * </ul>
 * <p>
 * The following parameters are applied in order to that date. Each one has this
 * structure. No spaces are allowed within a parameter.
 * <ol>
 * <li>+ or - or @: the direction of effect on the date (@ means absolute or at)
 * <li>&gt; or &lt; or &gt;= or &lt;=: next, prev, next or this, prev or this.
 * <li>### : the quantity of the effect; E and B can be used in conjunction with
 * the direction
 * <li>unit : the unit of the effect (case is not important)
 * <ul>
 * <li>(T)ime (@BTime and @ETime are valid giving 0:0:0.0 and 23:59:59.999
 * respectively)
 * <li>(Y)ear
 * <li>(M)onth
 * <li>(W)eekOfYear (B and E work on the current week)
 * <li>Week(O)fMonth (B and E work on the current week)
 * <li>(D)ay
 * <li>D(a)yOfWeek (B and E work on the current week, @ is current week, - is
 * previous week, and + is next week. Sunday is the first day of the week.)
 * <li>(H)our
 * <li>M(i)nute
 * <li>(S)econd
 * <li>Mi(l)lisecond or ms
 * </ul>
 * </ol>
 * <h1>examples</h1>
 * <h2>The beginning of today</h2> _dateTime(now @bday) <br>
 * <h2>The beginning of yesterday</h2> _dateTime(now -1day @bday) <br>
 * <h2>The end of yesterday</h2> _dateTime(now -1day @eday) <br>
 * <h2>Monday of this week</h2> _dateTime(now @2dayOfWeek) <br>
 * <h2>Monday of the week that contained 2010/04/09</h2>
 * _dateTime(2010/04/09 @2dayOfWeek) <br>
 * <h2>Same day and time last week</h2> _dateTime(now -1week) <br>
 * <h2>Same day last week but at the end of that day.</h2> _dateTime(now
 * -1week @eday) <br>
 * <h2>The first day of this month</h2> _dateTime(now @1d) <br>
 * <h2>The last day of last month</h2> _dateTime(now -1month @emonth) <br>
 * This could be done in different ways (like all of the others too), <br>
 * _dateTime(now @1d -1d @ed) <br>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public interface ICalendarFactory
{
    /**
     * <p>
     * asFormula.
     * </p>
     *
     * @param calendar
     *            a {@link java.util.Calendar} object.
     * @return a {@link java.lang.String} object.
     */
    String asFormula(final Calendar calendar);

    /**
     * <p>
     * atImpl.
     * </p>
     *
     * @param milliseconds
     *            a long.
     * @return a {@link java.util.Calendar} object.
     */
    Calendar atImpl(long milliseconds);

    /**
     * <p>
     * modifyImpl.
     * </p>
     *
     * @param startingDate
     *            a {@link java.util.Calendar} object.
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException
     *             if any.
     */
    Calendar modifyImpl(Calendar startingDate, String... adjustmentsArray) throws ParseException;

    /**
     * <p>
     * modifyImpl.
     * </p>
     *
     * @param startingDate
     *            a {@link java.util.Date} object.
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException
     *             if any.
     */
    Calendar modifyImpl(Date startingDate, String... adjustmentsArray) throws ParseException;

    /**
     * <p>
     * modifyImpl.
     * </p>
     *
     * @param startingMilliseconds
     *            a long.
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException
     *             if any.
     */
    Calendar modifyImpl(long startingMilliseconds, String... adjustmentsArray) throws ParseException;

    /**
     * <p>
     * noTimeImpl.
     * </p>
     *
     * @param startingDate
     *            a {@link java.util.Calendar} object.
     * @return a {@link java.util.Calendar} object.
     */
    Calendar noTimeImpl(Calendar startingDate);

    /**
     * <p>
     * noTimeImpl.
     * </p>
     *
     * @param startingDate
     *            a {@link java.util.Date} object.
     * @return a {@link java.util.Date} object.
     */
    Date noTimeImpl(Date startingDate);

    /**
     * <p>
     * nowImpl.
     * </p>
     *
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException
     *             if any.
     */
    Calendar nowImpl(String... adjustmentsArray) throws ParseException;

    /**
     * <p>
     * setBusinessDateImpl.
     * </p>
     *
     * @param businessDate
     *            a {@link java.util.Calendar} object.
     */
    void setBusinessDateImpl(Calendar businessDate);

    /**
     * <p>
     * todayImpl.
     * </p>
     *
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException
     *             if any.
     */
    Calendar todayImpl(String... adjustmentsArray) throws ParseException;
}
