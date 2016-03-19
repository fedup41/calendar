package com.obdobion.calendar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class parses phrases that will be used to compute a date. That date will
 * be returned as a Calendar.
 * <p>
 * The first token can be a special date function that is relative to the
 * current date.
 * <p>
 * <ul>
 * <li>Today or Now
 * <li>specific date in a format that is supported by the Date argument.
 * </ul>
 * <p>
 * The following parameters are applied in order to that date. Each one has this
 * structure. No spaces are allowed within a parameter.
 * <ol>
 * <li>+ or - or @: the direction of effect on the date (@ means absolute or at)
 * <li>>or < or >= or <=: next, prev, next or this, prev or this.
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
 * <h4>examples</h4>
 * <h5>The beginning of today</h5>
 * _dateTime(now @bday) <br>
 * <h5>The beginning of yesterday</h5>
 * _dateTime(now -1day @bday) <br>
 * <h5>The end of yesterday</h5>
 * _dateTime(now -1day @eday) <br>
 * <h5>Monday of this week</h5>
 * _dateTime(now @2dayOfWeek) <br>
 * <h5>Monday of the week that contained 2010/04/09</h5>
 * _dateTime(2010/04/09 @2dayOfWeek) <br>
 * <h5>Same day and time last week</h5>
 * _dateTime(now -1week) <br>
 * <h5>Same day last week but at the end of that day.</h5>
 * _dateTime(now -1week @eday) <br>
 * <h5>The first day of this month</h5>
 * _dateTime(now @1d) <br>
 * <h5>The last day of last month</h5>
 * _dateTime(now -1month @emonth) <br>
 * This could be done in different ways (like all of the others too), <br>
 * _dateTime(now @1d -1d @ed) <br>
 * 
 * @author Chris DeGreef
 * 
 */
class CalendarFactoryImpl implements ICalendarFactory
{
    long businessDateAdjustment;

    public String asFormula (final Calendar calendar)
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("@").append(calendar.get(Calendar.YEAR)).append("year");
        sb.append(" @").append(calendar.get(Calendar.MONTH) + 1).append("month");
        sb.append(" @").append(calendar.get(Calendar.DAY_OF_MONTH)).append("day");
        sb.append(" @").append(calendar.get(Calendar.HOUR_OF_DAY)).append("hour");
        sb.append(" @").append(calendar.get(Calendar.MINUTE)).append("minute");
        sb.append(" @").append(calendar.get(Calendar.SECOND)).append("second");
        sb.append(" @").append(calendar.get(Calendar.MILLISECOND)).append("millisecond");
        return sb.toString();
    }

    void adjustForBusinessDate (final Calendar startingDate)
    {
        long bMs = businessDateAdjustment;
        if (bMs > 0)
            while (bMs > 0)
            {
                int bMsPart;
                if (bMs > Integer.MAX_VALUE)
                    bMsPart = Integer.MAX_VALUE;
                else
                    bMsPart = (int) bMs;
                startingDate.add(Calendar.MILLISECOND, bMsPart);
                bMs -= bMsPart;
            }
        else
            while (bMs < 0)
            {
                int bMsPart;
                if (bMs < Integer.MIN_VALUE)
                    bMsPart = Integer.MIN_VALUE;
                else
                    bMsPart = (int) bMs;
                startingDate.add(Calendar.MILLISECOND, bMsPart);
                bMs -= bMsPart;
            }
    }

    Calendar applyAdjustments (
            final Calendar startingDate,
            final String... adjustmentsArray)
            throws ParseException
    {
        if (adjustmentsArray != null)
        {
            final List<DateAdjustment> adjustments = new ArrayList<>();
            for (final String adjs : adjustmentsArray)
            {
                if (adjs.trim().length() == 0)
                    break;
                final String[] singleAdjs = adjs.split(" +");
                for (final String adj : singleAdjs)
                    adjustments.add(new DateAdjustment(adj));
            }

            for (final DateAdjustment adj : adjustments)
            {
                adj.adjust(startingDate);
            }
        }
        return startingDate;
    }

    public Calendar atImpl (final long milliseconds)
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        return cal;
    }

    public Calendar modifyImpl (
            final Calendar startingDate,
            final String... adjustmentsArray)
            throws ParseException
    {
        return applyAdjustments(startingDate, adjustmentsArray);
    }

    public Calendar modifyImpl (
            final Date startingDate,
            final String... adjustmentsArray)
            throws ParseException
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(startingDate);
        return applyAdjustments(cal, adjustmentsArray);
    }

    public Calendar modifyImpl (
            final long startingMilliseconds,
            final String... adjustmentsArray)
            throws ParseException
    {
        final Calendar cal = atImpl(startingMilliseconds);
        return applyAdjustments(cal, adjustmentsArray);
    }

    public Calendar noTimeImpl (
            final Calendar startingDate)
    {
        startingDate.set(Calendar.HOUR_OF_DAY, 0);
        startingDate.set(Calendar.MINUTE, 0);
        startingDate.set(Calendar.SECOND, 0);
        startingDate.set(Calendar.MILLISECOND, 0);
        return startingDate;
    }

    public Date noTimeImpl (
            final Date startingDate)
    {
        final Calendar startingCal = Calendar.getInstance();
        startingCal.setTime(startingDate);
        startingCal.set(Calendar.HOUR_OF_DAY, 0);
        startingCal.set(Calendar.MINUTE, 0);
        startingCal.set(Calendar.SECOND, 0);
        startingCal.set(Calendar.MILLISECOND, 0);
        return startingCal.getTime();
    }

    public Calendar nowImpl (
            final String... adjustmentsArray)
            throws ParseException
    {
        final Calendar startingDate = Calendar.getInstance();
        adjustForBusinessDate(startingDate);
        return applyAdjustments(startingDate, adjustmentsArray);
    }

    public void setBusinessDateImpl (
            final Calendar businessDate)
    {
        if (businessDate == null)
        {
            businessDateAdjustment = 0;
            return;
        }
        this.businessDateAdjustment = businessDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
    }

    public Calendar todayImpl (
            final String... adjustmentsArray)
            throws ParseException
    {
        final Calendar startingDate = Calendar.getInstance();
        adjustForBusinessDate(startingDate);
        return applyAdjustments(noTimeImpl(startingDate), adjustmentsArray);
    }

}
