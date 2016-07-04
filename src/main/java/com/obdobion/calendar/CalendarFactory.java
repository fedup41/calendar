package com.obdobion.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
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
 * <li>+ or - or =: the direction of effect on the date (= means absolute or at)
 * <li>&gt; or &lt; or &gt;= or &lt;=: next, prev, next or this, prev or this.
 * <li>### : the quantity of the effect; E and B can be used in conjunction with
 * the direction
 * <li>unit : the unit of the effect (case is not important)
 * <ul>
 * <li>(T)ime (=BTime and =ETime are valid giving 0:0:0.0 and 23:59:59.999
 * respectively)
 * <li>(Y)ear
 * <li>(M)onth
 * <li>(W)eekOfYear (B and E work on the current week)
 * <li>Week(O)fMonth (B and E work on the current week)
 * <li>(D)ay
 * <li>D(a)yOfWeek (B and E work on the current week, = is current week, - is
 * previous week, and + is next week. Sunday is the first day of the week.)
 * <li>(H)our
 * <li>M(i)nute
 * <li>(S)econd
 * <li>Mi(l)lisecond or ms
 * </ul>
 * </ol>
 * <h1>examples</h1>
 * <h2>The beginning of today</h2> _dateTime(now =bday) <br>
 * <h2>The beginning of yesterday</h2> _dateTime(now -1day =bday) <br>
 * <h2>The end of yesterday</h2> _dateTime(now -1day =eday) <br>
 * <h2>Monday of this week</h2> _dateTime(now =2dayOfWeek) <br>
 * <h2>Monday of the week that contained 2010/04/09</h2> _dateTime(2010/04/09
 * =2dayOfWeek) <br>
 * <h2>Same day and time last week</h2> _dateTime(now -1week) <br>
 * <h2>Same day last week but at the end of that day.</h2> _dateTime(now -1week
 * =eday) <br>
 * <h2>The first day of this month</h2> _dateTime(now =1d) <br>
 * <h2>The last day of last month</h2> _dateTime(now -1month =emonth) <br>
 * This could be done in different ways (like all of the others too), <br>
 * _dateTime(now =1d -1d =ed) <br>
 *
 * @author Chris DeGreef
 *
 */
public class CalendarFactory
{
    static private ICalendarFactory instance;
    static boolean                  inDebug           = false;
    static final SimpleDateFormat   jsonDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    static public String asFormula (final Calendar calendar)
    {
        return getInstance().asFormula(calendar);
    }

    static public String asFormula (final Date date)
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return asFormula(cal);
    }

    static public String asJSON (final Calendar calendar)
    {
        return jsonDateFormatter.format(calendar.getTime());
    }

    static public Calendar at (final long milliseconds)
    {
        return getInstance().atImpl(milliseconds);
    }

    static public ICalendarFactory getInstance ()
    {
        if (instance == null)
            instance = new CalendarFactoryImpl();
        return instance;
    }

    static public boolean isInDebug ()
    {
        return inDebug;
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called.
     *
     * @param startingDate
     * @param adjustmentsArray
     * @return
     */
    static public Calendar modify (final Calendar startingDate, final String... adjustmentsArray)
    {
        try
        {
            return getInstance().modifyImpl(startingDate, adjustmentsArray);
        } catch (final ParseException e)
        {
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called.
     *
     * @param startingDate
     * @param adjustmentsArray
     * @return
     */
    static public Calendar modify (final Date startingDate, final String... adjustmentsArray)
    {
        try
        {
            return getInstance().modifyImpl(startingDate, adjustmentsArray);
        } catch (final ParseException e)
        {
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }

    static public Calendar modify (final long startingMilliseconds, final String... adjustmentsArray)
    {
        try
        {
            return getInstance().modifyImpl(startingMilliseconds, adjustmentsArray);
        } catch (final ParseException e)
        {
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called.
     *
     * @param startingJSONDate
     * @param adjustmentsArray
     * @return
     */
    static public Calendar modifyJSON (final String startingJSONDate, final String... adjustmentsArray)
    {
        try
        {
            return getInstance().modifyImpl(jsonDateFormatter.parse(startingJSONDate), adjustmentsArray);
        } catch (final ParseException e)
        {
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }

    static public Calendar noTime (final Calendar startingDate)
    {
        return getInstance().noTimeImpl(startingDate);
    }

    static public Date noTime (final Date startingDate)
    {
        return getInstance().noTimeImpl(startingDate);
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called. The computation starts at
     * the exact millisecond this method is called
     *
     * @param adjustmentsArray
     * @return
     */
    static public Calendar now (final String... adjustmentsArray)
    {
        try
        {
            return getInstance().nowImpl(adjustmentsArray);
        } catch (final ParseException e)
        {
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }

    static public Calendar nowX (final String... adjustmentsArray) throws ParseException
    {
        return getInstance().nowImpl(adjustmentsArray);
    }

    static public ICalendarFactory reset (final ICalendarFactory newFactory)
    {
        final ICalendarFactory oldFactory = instance;
        instance = newFactory;
        return oldFactory;
    }

    static public void setBusinessDate (final Calendar businessDate)
    {
        getInstance().setBusinessDateImpl(businessDate);
    }

    static public void setInDebug (final boolean inDebug_parm)
    {
        inDebug = inDebug_parm;
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called. The computation starts at
     * the beginning of the current date.
     *
     * @param adjustmentsArray
     * @return
     */
    static public Calendar today (final String... adjustmentsArray)
    {
        try
        {
            return getInstance().todayImpl(adjustmentsArray);
        } catch (final ParseException e)
        {
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }

}
