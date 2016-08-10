package com.obdobion.calendar;

import java.util.Calendar;

import org.junit.Assert;

/**
 * <p>CalendarFactoryHelper class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class CalendarFactoryHelper
{

    /**
     * <p>startExpectedComputed.</p>
     *
     * @param startingDateCommand a {@link java.lang.String} object.
     * @param expectedDateCommand a {@link java.lang.String} object.
     * @param computedDateCommand a {@link java.lang.String} object.
     * @throws java.lang.Exception if any.
     */
    static public void startExpectedComputed(
            final String startingDateCommand,
            final String expectedDateCommand,
            final String computedDateCommand)
                    throws Exception
    {
        startExpectedComputed(startingDateCommand, expectedDateCommand, computedDateCommand, false);
    }

    /**
     * <p>startExpectedComputed.</p>
     *
     * @param startingDateCommand a {@link java.lang.String} object.
     * @param expectedDateCommand a {@link java.lang.String} object.
     * @param computedDateCommand a {@link java.lang.String} object.
     * @param compareMillies a boolean.
     * @throws java.lang.Exception if any.
     */
    static public void startExpectedComputed(
            final String startingDateCommand,
            final String expectedDateCommand,
            final String computedDateCommand,
            final boolean compareMillies)
                    throws Exception
    {
        CalendarFactory.setBusinessDate(null);
        CalendarFactory.setBusinessDate(CalendarFactory.nowX(startingDateCommand));
        final Calendar expectedCalendar = CalendarFactory.nowX(expectedDateCommand);

        CalendarFactory.setBusinessDate(null);
        CalendarFactory.setBusinessDate(CalendarFactory.nowX(startingDateCommand));
        final Calendar computedCalendar = CalendarFactory.nowX(computedDateCommand);

        if (!compareMillies)
        {
            expectedCalendar.set(Calendar.MILLISECOND, 0);
            computedCalendar.set(Calendar.MILLISECOND, 0);
        }

        Assert.assertEquals(expectedCalendar.getTime(), computedCalendar.getTime());
    }

}
