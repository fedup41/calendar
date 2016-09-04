package com.obdobion.calendar;

import java.time.LocalDateTime;

import org.junit.Assert;

/**
 * <p>
 * CalendarFactoryHelper class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class CalendarFactoryHelper
{

    /**
     * <p>
     * startExpectedComputed.
     * </p>
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
     * <p>
     * startExpectedComputed.
     * </p>
     *
     * @param startingDateCommand a {@link java.lang.String} object.
     * @param expectedDateCommand a {@link java.lang.String} object.
     * @param computedDateCommand a {@link java.lang.String} object.
     * @param comparingNanos a boolean.
     * @throws java.lang.Exception if any.
     */
    static public void startExpectedComputed(
            final String startingDateCommand,
            final String expectedDateCommand,
            final String computedDateCommand,
            final boolean comparingNanos)
                    throws Exception
    {
        CalendarFactory.setBusinessDate(null);
        CalendarFactory.setBusinessDate(CalendarFactory.now(startingDateCommand));
        final LocalDateTime expectedLDT = CalendarFactory.now(expectedDateCommand);

        CalendarFactory.setBusinessDate(null);
        CalendarFactory.setBusinessDate(CalendarFactory.now(startingDateCommand));
        final LocalDateTime computedLDT = CalendarFactory.now(computedDateCommand);

        Assert.assertEquals("YEAR (" + expectedLDT.toString() + ") != (" + computedLDT.toString() + ")",
                expectedLDT.getYear(), computedLDT.getYear());
        Assert.assertEquals("MONTH (" + expectedLDT.toString() + ") != (" + computedLDT.toString() + ")",
                expectedLDT.getMonthValue(), computedLDT.getMonthValue());
        Assert.assertEquals("DAY (" + expectedLDT.toString() + ") != (" + computedLDT.toString() + ")",
                expectedLDT.getDayOfMonth(), computedLDT.getDayOfMonth());
        Assert.assertEquals("HOUR (" + expectedLDT.toString() + ") != (" + computedLDT.toString() + ")",
                expectedLDT.getHour(), computedLDT.getHour());
        Assert.assertEquals("MINUTE (" + expectedLDT.toString() + ") != (" + computedLDT.toString() + ")",
                expectedLDT.getMinute(), computedLDT.getMinute());
        Assert.assertEquals("SECOND (" + expectedLDT.toString() + ") != (" + computedLDT.toString() + ")",
                expectedLDT.getSecond(), computedLDT.getSecond());
        /*-
        Assert.assertEquals("NANO (" + expectedLDT.toString() + ") != (" + computedLDT.toString() + ")",
            expectedLDT.getNano(), computedLDT.getNano());
        */

    }
}
