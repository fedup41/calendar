package com.obdobion.calendar;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris DeGreef fedupforone@gmail.com
 *
 */
class CalendarFactoryImpl implements ICalendarFactory
{
    LocalDateTime overrideForNowAndToday;

    LocalDateTime applyAdjustments(final LocalDateTime startingDate, final String... adjustmentsArray)
            throws ParseException
    {
        LocalDateTime modifiedDateTime = startingDate;
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
                modifiedDateTime = adj.adjust(modifiedDateTime);
        }
        return modifiedDateTime;
    }

    /** {@inheritDoc} */
    @Override
    public String asFormula(final LocalDateTime ldt)
    {
        final char dir = AdjustmentDirection.AT.firstChar;

        final StringBuilder sb = new StringBuilder();
        sb.append("" + dir).append(ldt.getYear()).append("year");
        sb.append(" " + dir).append(ldt.getMonthValue()).append("month");
        sb.append(" " + dir).append(ldt.getDayOfMonth()).append("day");
        sb.append(" " + dir).append(ldt.getHour()).append("hour");
        sb.append(" " + dir).append(ldt.getMinute()).append("minute");
        sb.append(" " + dir).append(ldt.getSecond()).append("second");
        sb.append(" " + dir).append(ldt.getNano()).append("nanosecond");
        return sb.toString();
    }

    /** {@inheritDoc} */
    @Override
    public LocalDateTime modifyImpl(final LocalDateTime startingDate, final String... adjustmentsArray)
            throws ParseException
    {
        return applyAdjustments(startingDate, adjustmentsArray);
    }

    /** {@inheritDoc} */
    @Override
    public LocalDateTime noTimeImpl(final LocalDateTime startingDate)
    {
        return LocalDateTime.of(
                startingDate.getYear(),
                startingDate.getMonth(),
                startingDate.getDayOfMonth(),
                0, 0, 0, 0);
    }

    /** {@inheritDoc} */
    @Override
    public LocalDateTime nowImpl(final String... adjustmentsArray) throws ParseException
    {
        return applyAdjustments(overrideIfNecessary(LocalDateTime.now()), adjustmentsArray);
    }

    LocalDateTime overrideIfNecessary(final LocalDateTime startingDate)
    {
        if (overrideForNowAndToday != null)
            return overrideForNowAndToday;
        return startingDate;
    }

    /** {@inheritDoc} */
    @Override
    public void setOverrideForSystemTime(final LocalDateTime overrideDateAndTime)
    {
        overrideForNowAndToday = overrideDateAndTime;
    }

    /** {@inheritDoc} */
    @Override
    public LocalDateTime todayImpl(final String... adjustmentsArray) throws ParseException
    {
        return applyAdjustments(noTimeImpl(overrideIfNecessary(LocalDateTime.now())), adjustmentsArray);
    }
}
