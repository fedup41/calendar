package com.obdobion.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>CalendarFactory class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class CalendarFactory
{
    static boolean                  inDebug           = false;
    static private ICalendarFactory instance;
    static final SimpleDateFormat   jsonDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    /**
     * <p>
     * asFormula.
     * </p>
     *
     * @param calendar
     *            a {@link java.util.Calendar} object.
     * @return a {@link java.lang.String} object.
     */
    static public String asFormula(final Calendar calendar)
    {
        return getInstance().asFormula(calendar);
    }

    /**
     * <p>
     * asFormula.
     * </p>
     *
     * @param date
     *            a {@link java.util.Date} object.
     * @return a {@link java.lang.String} object.
     */
    static public String asFormula(final Date date)
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return asFormula(cal);
    }

    /**
     * <p>
     * asJSON.
     * </p>
     *
     * @param calendar
     *            a {@link java.util.Calendar} object.
     * @return a {@link java.lang.String} object.
     */
    static public String asJSON(final Calendar calendar)
    {
        return jsonDateFormatter.format(calendar.getTime());
    }

    /**
     * <p>
     * at.
     * </p>
     *
     * @param milliseconds
     *            a long.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar at(final long milliseconds)
    {
        return getInstance().atImpl(milliseconds);
    }

    /**
     * <p>
     * Getter for the field <code>instance</code>.
     * </p>
     *
     * @return a {@link com.obdobion.calendar.ICalendarFactory} object.
     */
    static public ICalendarFactory getInstance()
    {
        if (instance == null)
            instance = new CalendarFactoryImpl();
        return instance;
    }

    /**
     * <p>
     * isInDebug.
     * </p>
     *
     * @return a boolean.
     */
    static public boolean isInDebug()
    {
        return inDebug;
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called.
     *
     * @param startingDate
     *            a {@link java.util.Calendar} object.
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar modify(final Calendar startingDate, final String... adjustmentsArray)
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
     *            a {@link java.util.Date} object.
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar modify(final Date startingDate, final String... adjustmentsArray)
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
     * <p>
     * modify.
     * </p>
     *
     * @param startingMilliseconds
     *            a long.
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar modify(final long startingMilliseconds, final String... adjustmentsArray)
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
     *            a {@link java.lang.String} object.
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar modifyJSON(final String startingJSONDate, final String... adjustmentsArray)
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

    /**
     * <p>
     * noTime.
     * </p>
     *
     * @param startingDate
     *            a {@link java.util.Calendar} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar noTime(final Calendar startingDate)
    {
        return getInstance().noTimeImpl(startingDate);
    }

    /**
     * <p>
     * noTime.
     * </p>
     *
     * @param startingDate
     *            a {@link java.util.Date} object.
     * @return a {@link java.util.Date} object.
     */
    static public Date noTime(final Date startingDate)
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
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar now(final String... adjustmentsArray)
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

    /**
     * <p>
     * nowX.
     * </p>
     *
     * @param adjustmentsArray
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException
     *             if any.
     */
    static public Calendar nowX(final String... adjustmentsArray) throws ParseException
    {
        return getInstance().nowImpl(adjustmentsArray);
    }

    /**
     * <p>
     * reset.
     * </p>
     *
     * @param newFactory
     *            a {@link com.obdobion.calendar.ICalendarFactory} object.
     * @return a {@link com.obdobion.calendar.ICalendarFactory} object.
     */
    static public ICalendarFactory reset(final ICalendarFactory newFactory)
    {
        final ICalendarFactory oldFactory = instance;
        instance = newFactory;
        return oldFactory;
    }

    /**
     * <p>
     * setBusinessDate.
     * </p>
     *
     * @param businessDate
     *            a {@link java.util.Calendar} object.
     */
    static public void setBusinessDate(final Calendar businessDate)
    {
        getInstance().setBusinessDateImpl(businessDate);
    }

    /**
     * <p>
     * Setter for the field <code>inDebug</code>.
     * </p>
     *
     * @param inDebug_parm
     *            a boolean.
     */
    static public void setInDebug(final boolean inDebug_parm)
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
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar today(final String... adjustmentsArray)
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
