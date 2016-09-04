package com.obdobion.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * CalendarFactory class.
 * </p>
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
     * asCalendar.
     * </p>
     *
     * @param ldt a {@link java.time.LocalDateTime} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public Calendar asCalendar(final LocalDateTime ldt)
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(asDateLong(ldt)));
        return cal;
    }

    /**
     * <p>
     * asDate.
     * </p>
     *
     * @param ldt a {@link java.time.LocalDateTime} object.
     * @return a {@link java.util.Date} object.
     */
    static public Date asDate(final LocalDateTime ldt)
    {
        return new Date(asDateLong(ldt));
    }

    /**
     * <p>
     * asDateLong.
     * </p>
     *
     * @param ldt a {@link java.time.LocalDateTime} object.
     * @return a long.
     */
    static public long asDateLong(final LocalDateTime ldt)
    {
        final long seconds = ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
        final long totalMillisSinceEpoch = (seconds * 1000) + ldt.getNano() / 1000000;
        return totalMillisSinceEpoch;
    }

    /**
     * <p>
     * asFormula.
     * </p>
     *
     * @param calendar a {@link java.util.Calendar} object.
     * @return a {@link java.lang.String} object.
     */
    static public String asFormula(final Calendar calendar)
    {

        return getInstance().asFormula(convert(calendar));
    }

    /**
     * <p>
     * asFormula.
     * </p>
     *
     * @param date a {@link java.util.Date} object.
     * @return a {@link java.lang.String} object.
     */
    static public String asFormula(final Date date)
    {
        return asFormula(convert(date));
    }

    /**
     * <p>
     * asFormula.
     * </p>
     *
     * @param calendar a {@link java.time.LocalDateTime} object.
     * @return a {@link java.lang.String} object.
     * @since 2.0.0
     */
    static public String asFormula(final LocalDateTime calendar)
    {
        return getInstance().asFormula(calendar);
    }

    /**
     * <p>
     * asJSON.
     * </p>
     *
     * @param calendar a {@link java.util.Calendar} object.
     * @return a {@link java.lang.String} object.
     */
    static public String asJSON(final Calendar calendar)
    {
        return asJSON(convert(calendar));
    }

    /**
     * <p>
     * asJSON.
     * </p>
     *
     * @param ldt a {@link java.util.Calendar} object.
     * @return a {@link java.lang.String} object.
     * @since 2.0.0
     */
    static public String asJSON(final LocalDateTime ldt)
    {
        return jsonDateFormatter.format(new Date(asDateLong(ldt)));
    }

    /**
     * <p>
     * at.
     * </p>
     *
     * @param milliseconds a long.
     * @return a {@link java.util.Calendar} object.
     */
    static public LocalDateTime at(final long milliseconds)
    {
        /*
         * Only seconds are supported but legacy is passing millis
         */
        return convert(milliseconds / 1000);
    }

    /**
     * <p>
     * convert.
     * </p>
     *
     * @param calendar a {@link java.util.Calendar} object.
     * @return a {@link java.time.LocalDateTime} object.
     * @since 2.0.0
     */
    static public LocalDateTime convert(final Calendar calendar)
    {
        return calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * <p>
     * convert.
     * </p>
     *
     * @param date a {@link java.util.Date} object.
     * @return a {@link java.time.LocalDateTime} object.
     * @since 2.0.0
     */
    static public LocalDateTime convert(final Date date)
    {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * <p>
     * convert.
     * </p>
     *
     * @param date a {@link java.time.LocalDate} object.
     * @return a {@link java.time.LocalDateTime} object.
     * @since 2.0.1
     */
    static public LocalDateTime convert(final LocalDate date)
    {
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    /**
     * <p>
     * convert.
     * </p>
     *
     * @param time a {@link java.time.LocalTime} object.
     * @return a {@link java.time.LocalDateTime} object.
     * @since 2.0.1
     */
    static public LocalDateTime convert(final LocalTime time)
    {
        return LocalDateTime.of(LocalDate.MIN, time);
    }

    /**
     * <p>
     * convert.
     * </p>
     *
     * @param epochSecond a long.
     * @return a {@link java.time.LocalDateTime} object.
     * @since 2.0.0
     */
    static public LocalDateTime convert(final long epochSecond)
    {
        final Instant instant = Instant.ofEpochSecond(epochSecond);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
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
     * @param startingDate a {@link java.util.Calendar} object.
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime modify(final Calendar startingDate, final String... adjustmentsArray)
            throws ParseException
    {
        return getInstance().modifyImpl(convert(startingDate), adjustmentsArray);
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called.
     *
     * @param startingDate a {@link java.util.Date} object.
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime modify(final Date startingDate, final String... adjustmentsArray)
            throws ParseException
    {
        return getInstance().modifyImpl(convert(startingDate), adjustmentsArray);
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called.
     *
     * @param startingDate a {@link java.util.Calendar} object.
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime modify(final LocalDate startingDate, final String... adjustmentsArray)
            throws ParseException
    {
        return getInstance().modifyImpl(convert(startingDate), adjustmentsArray);
    }

    /**
     * <p>
     * modify.
     * </p>
     *
     * @param startingDate a {@link java.time.LocalDateTime} object.
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.time.LocalDateTime} object.
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime modify(final LocalDateTime startingDate, final String... adjustmentsArray)
            throws ParseException
    {
        return getInstance().modifyImpl(startingDate, adjustmentsArray);
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called.
     *
     * @param startingDate a {@link java.time.LocalTime} object.
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime modify(final LocalTime startingDate, final String... adjustmentsArray)
            throws ParseException
    {
        return getInstance().modifyImpl(convert(startingDate), adjustmentsArray);
    }

    /**
     * <p>
     * modify.
     * </p>
     *
     * @param startingMilliseconds a long.
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime modify(final long startingMilliseconds, final String... adjustmentsArray)
            throws ParseException
    {
        return getInstance().modifyImpl(convert(startingMilliseconds), adjustmentsArray);
    }

    /**
     * <p>
     * modify.
     * </p>
     *
     * @param startingDate a {@link java.lang.String} object.
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.time.LocalDateTime} object.
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime modify(final String startingDate, final String... adjustmentsArray)
            throws ParseException
    {
        return getInstance().modifyImpl(TemporalHelper.parseWithPredefinedParsers(startingDate), adjustmentsArray);
    }

    /**
     * <p>
     * noTime.
     * </p>
     *
     * @param startingDate a {@link java.util.Calendar} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public LocalDateTime noTime(final Calendar startingDate)
    {
        return getInstance().noTimeImpl(convert(startingDate));
    }

    /**
     * <p>
     * noTime.
     * </p>
     *
     * @param startingDate a {@link java.util.Date} object.
     * @return a {@link java.util.Date} object.
     */
    static public LocalDateTime noTime(final Date startingDate)
    {
        return getInstance().noTimeImpl(convert(startingDate));
    }

    /**
     * <p>
     * noTime.
     * </p>
     *
     * @param startingDate a {@link java.util.Date} object.
     * @return a {@link java.util.Date} object.
     */
    static public LocalDateTime noTime(final LocalDateTime startingDate)
    {
        return getInstance().noTimeImpl(startingDate);
    }

    /**
     * The adjustments are separated by a space. Multiple elements are
     * acceptable and are assumed to be more adjusts. This just provides
     * flexibility in how this method can be called. The computation starts at
     * the exact millisecond this method is called
     *
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime now(final String... adjustmentsArray)
            throws ParseException
    {
        return getInstance().nowImpl(adjustmentsArray);
    }

    /**
     * <p>
     * reset.
     * </p>
     *
     * @param newFactory a {@link com.obdobion.calendar.ICalendarFactory}
     *            object.
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
     * @param businessDate a {@link java.util.Calendar} object.
     */
    static public void setBusinessDate(final LocalDateTime businessDate)
    {
        getInstance().setOverrideForSystemTime(businessDate);
    }

    /**
     * <p>
     * Setter for the field <code>inDebug</code>.
     * </p>
     *
     * @param inDebug_parm a boolean.
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
     * @param adjustmentsArray a {@link java.lang.String} object.
     * @return a {@link java.util.Calendar} object.
     */
    static public LocalDateTime today(final String... adjustmentsArray)
    {
        try
        {
            return getInstance().todayImpl(adjustmentsArray);
        } catch (final ParseException e)
        {
            e.printStackTrace();
            return LocalDateTime.now();
        }
    }

}
