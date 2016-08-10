package com.obdobion.calendar;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>DateHourTest class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class DateHourTest
{
    /**
     * <p>Constructor for DateHourTest.</p>
     */
    public DateHourTest()
    {

    }

    /**
     * <p>add_hour.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void add_hour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =0hours =10minutes", "=1h", "+1hour");
    }

    /**
     * <p>add_invalidBeginning.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void add_invalidBeginning()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=bt =0hours =10minutes", "", "+bhour");
            Assert.fail("expected an exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    /**
     * <p>atBeforeCurrentTimeResultsInPastTime.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atBeforeCurrentTimeResultsInPastTime()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours", "=9hours", "=9hours");
    }

    /**
     * <p>atEndOfHourWhenAtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atEndOfHourWhenAtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =13hours", "", "=ehour");
    }

    /**
     * <p>atEndOfHourWhenNotAtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atEndOfHourWhenNotAtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =13hours", "=et =13hour", "=ehours");
    }

    /**
     * <p>bug_nextOrThisAfterTheTimeInTheCurrentDay.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void bug_nextOrThisAfterTheTimeInTheCurrentDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =20hours =45minutes", "=12h =00i", "+0d =btime >11h >0min");
    }

    /**
     * <p>bug_nextOrThisAfterTheTimeInTheCurrentDayAtBegHour.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void bug_nextOrThisAfterTheTimeInTheCurrentDayAtBegHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =20hours =45minutes", "+1d =11h =00i", "+0d >11h =bhour");
    }

    /**
     * <p>bug_nextOrThisAfterTheTimeInTheCurrentDayNoBTIME.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void bug_nextOrThisAfterTheTimeInTheCurrentDayNoBTIME()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =20hours =45minutes", "+1d =12h =00i", "+0d >11h >0min");
    }

    /**
     * <p>greaterBeforeCurrentTimeResultsInFutureTime.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void greaterBeforeCurrentTimeResultsInFutureTime()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours", "+1d =9hours", ">9hours");
    }

    /**
     * <p>itsBeginningOfHour_prevOrThis.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsBeginningOfHour_prevOrThis()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours", "", "<=bhour");
    }

    /**
     * <p>itsFirstHour_nextOrThisMidnight.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsFirstHour_nextOrThisMidnight()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10minutes", "=bt =2h", ">=bhour");
    }

    /**
     * <p>itsFirstHour_prevOrThisMidnight.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsFirstHour_prevOrThisMidnight()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10minutes", "=bt =1h", "<=bhour");
    }

    /**
     * <p>itsJustAfterMidnightHour_thisMidnight.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsJustAfterMidnightHour_thisMidnight()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =0hours =10minutes", "=bt", "<bhour");
    }

    /**
     * <p>itsMidnight_prevHour.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsMidnight_prevHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt", "-1day =bt =23h", "<bhour");
    }

    /**
     * <p>itsMidnightHour_nextMidnight.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsMidnightHour_nextMidnight()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =0hours =0minutes", "=bt =1h", ">bhour");
    }

    /**
     * <p>itsMidnightHour_nextOrThisMidnight.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsMidnightHour_nextOrThisMidnight()
            throws Exception
    {
        // CalendarFactory.setInDebug(true);
        CalendarFactoryHelper.startExpectedComputed("=bt", "", ">=bhour");
        // CalendarFactory.setInDebug(false);
    }

    /**
     * <p>itsNoonHour_at5pm.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsNoonHour_at5pm()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours =10minutes",
                "=bt +17hours =10minutes",
                "=17hours =10minutes");
    }

    /**
     * <p>itsNoonHour_atMidnight.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void itsNoonHour_atMidnight()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours =10minutes", "=bt =12h", "=bh");
    }

    /**
     * <p>lessAfterTimeResultsInPastTime.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void lessAfterTimeResultsInPastTime()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours", "-1d =13hours", "<13hours");
    }

    /**
     * <p>lessBeforeTimeResultsInExactTime.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void lessBeforeTimeResultsInExactTime()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours", "=9hours", "<9hours");
    }

    /**
     * <p>lessOrEquals_equals.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void lessOrEquals_equals()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours =10minutes", "=0min", "<=12hours");
    }

    /**
     * <p>lessOrEquals_greater.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void lessOrEquals_greater()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours =10minutes", "-1d =13h =0min", "<=13hours");
    }

    /**
     * <p>nextEnd_AtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextEnd_AtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =12hours", "=13h", ">eh");
    }

    /**
     * <p>nextEnd_NotAtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextEnd_NotAtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours =10minutes", "=et =12h", ">eh");
    }

    /**
     * <p>nextOrThisEnd_AtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisEnd_AtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =12hours", "", ">=eh");
    }

    /**
     * <p>nextOrThisEnd_NotAtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisEnd_NotAtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours =10minutes", "=et =12h", ">=eh");
    }

    /**
     * <p>nextOrThisHour_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisHour_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =2hours =10minutes", "+1d =1h =0i", ">=1hour");
    }

    /**
     * <p>nextOrThisHour_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisHour_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10minutes", "=1h =0i", ">=1hour");
    }

    /**
     * <p>prevEnd_AtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevEnd_AtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =12hours", "=et =11h", "<eh");
    }

    /**
     * <p>prevEnd_NotAtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevEnd_NotAtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours", "=et =11h", "<eh");
    }

    /**
     * <p>prevOrThisEnd_AtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisEnd_AtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =12hours", "", "<=eh");
    }

    /**
     * <p>prevOrThisEnd_NotAtEnd.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisEnd_NotAtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours", "=et =11h", "<=eh");
    }

    /**
     * <p>subtract_hour.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtract_hour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =0hours =10minutes", "-1d =23h", "-1hour");
    }

    /**
     * <p>subtract_invalidBeginning.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtract_invalidBeginning()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=bt =0hours =10minutes", "", "-bhour");
            Assert.fail("expected an exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }
}
