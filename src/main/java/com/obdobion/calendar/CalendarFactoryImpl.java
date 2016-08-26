package com.obdobion.calendar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Chris DeGreef fedupforone@gmail.com
 *
 */
class CalendarFactoryImpl implements ICalendarFactory
{
    long businessDateAdjustment;

    void adjustForBusinessDate(final Calendar startingDate)
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

    Calendar applyAdjustments(
            final Calendar startingDate,
            final String... adjustmentsArray)
                    throws ParseException
    {
        if (adjustmentsArray != null)
        {
            final List<DateAdjustment> adjustments = new ArrayList<DateAdjustment>();
            for (final String adjs : adjustmentsArray)
            {
                if (adjs.trim().length() == 0)
                    break;
                final String[] singleAdjs = adjs.split(" +");
                for (final String adj : singleAdjs)
                    adjustments.add(new DateAdjustment(adj));
            }

            for (final DateAdjustment adj : adjustments)
                adj.adjust(startingDate);
        }
        return startingDate;
    }

    /** {@inheritDoc} */
    @Override
    public String asFormula(final Calendar calendar)
    {
        final char dir = AdjustmentDirection.AT.firstChar;

        final StringBuilder sb = new StringBuilder();
        sb.append("" + dir).append(calendar.get(Calendar.YEAR)).append("year");
        sb.append(" " + dir).append(calendar.get(Calendar.MONTH) + 1).append("month");
        sb.append(" " + dir).append(calendar.get(Calendar.DAY_OF_MONTH)).append("day");
        sb.append(" " + dir).append(calendar.get(Calendar.HOUR_OF_DAY)).append("hour");
        sb.append(" " + dir).append(calendar.get(Calendar.MINUTE)).append("minute");
        sb.append(" " + dir).append(calendar.get(Calendar.SECOND)).append("second");
        sb.append(" " + dir).append(calendar.get(Calendar.MILLISECOND)).append("millisecond");
        return sb.toString();
    }

    /** {@inheritDoc} */
    @Override
    public Calendar atImpl(final long milliseconds)
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        return cal;
    }

    /** {@inheritDoc} */
    @Override
    public Calendar modifyImpl(
            final Calendar startingDate,
            final String... adjustmentsArray)
                    throws ParseException
    {
        return applyAdjustments(startingDate, adjustmentsArray);
    }

    /** {@inheritDoc} */
    @Override
    public Calendar modifyImpl(
            final Date startingDate,
            final String... adjustmentsArray)
                    throws ParseException
    {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(startingDate);
        return applyAdjustments(cal, adjustmentsArray);
    }

    /** {@inheritDoc} */
    @Override
    public Calendar modifyImpl(
            final long startingMilliseconds,
            final String... adjustmentsArray)
                    throws ParseException
    {
        final Calendar cal = atImpl(startingMilliseconds);
        return applyAdjustments(cal, adjustmentsArray);
    }

    /** {@inheritDoc} */
    @Override
    public Calendar noTimeImpl(
            final Calendar startingDate)
    {
        startingDate.set(Calendar.HOUR_OF_DAY, 0);
        startingDate.set(Calendar.MINUTE, 0);
        startingDate.set(Calendar.SECOND, 0);
        startingDate.set(Calendar.MILLISECOND, 0);
        return startingDate;
    }

    /** {@inheritDoc} */
    @Override
    public Date noTimeImpl(
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

    /** {@inheritDoc} */
    @Override
    public Calendar nowImpl(
            final String... adjustmentsArray)
                    throws ParseException
    {
        final Calendar startingDate = Calendar.getInstance();
        adjustForBusinessDate(startingDate);
        return applyAdjustments(startingDate, adjustmentsArray);
    }

    /** {@inheritDoc} */
    @Override
    public void setBusinessDateImpl(
            final Calendar businessDate)
    {
        if (businessDate == null)
        {
            businessDateAdjustment = 0;
            return;
        }
        businessDateAdjustment = businessDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
    }

    /** {@inheritDoc} */
    @Override
    public Calendar todayImpl(
            final String... adjustmentsArray)
                    throws ParseException
    {
        final Calendar startingDate = Calendar.getInstance();
        adjustForBusinessDate(startingDate);
        return applyAdjustments(noTimeImpl(startingDate), adjustmentsArray);
    }

}
