package com.obdobion.calendar;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * DateMinuteTest class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class DateMinuteTest
{
    /**
     * <p>
     * Constructor for DateMinuteTest.
     * </p>
     *
     * @since 2.0.0
     */
    public DateMinuteTest()
    {

    }

    /**
     * <p>
     * add_invalidBeginning.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void add_invalidBeginning()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=bt =0hours =10minutes", "", "+bminute");
            Assert.fail("expected an exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    /**
     * <p>
     * add_minute.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void add_minute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =0hours =10minutes", "=11min", "+1minute");
    }

    /**
     * <p>
     * atBeforeCurrentTimeResultsInPastTime.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void atBeforeCurrentTimeResultsInPastTime()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours =30min", "-15minutes", "=15minutes");
    }

    /**
     * <p>
     * atEndWhenAtEnd.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void atEndWhenAtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =13hours", "", "=eminute");
    }

    /**
     * <p>
     * atEndWhenNotAtEnd.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void atEndWhenNotAtEnd()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =13hours =4min", "=et =13hour =4minutes", "=eminutes");
    }

    /**
     * <p>
     * beginningAt.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void beginningAt()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=bt =1hours =10min =1sec =2ms",
                "=bt =1hours =10min =0sec =0ms",
                "=bminute");
    }

    /**
     * <p>
     * beginningNext.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void beginningNext()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=bt =1hours =10min =1sec =1ms", "=bt =1hours =11min =0s =0n", ">bmin", true);
    }

    /**
     * <p>
     * beginningNextOrThis_beginning.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void beginningNextOrThis_beginning()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=bt =1hours =10min", ">=bmin");
    }

    /**
     * <p>
     * beginningNextOrThis_notBeginning.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void beginningNextOrThis_notBeginning()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=bt =1hours =10min =15sec =30ms", "=11min =0s =0n", ">=bmin");
    }

    /**
     * <p>
     * beginningPrev_after.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void beginningPrev_after()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min =1sec", "=bt =1hours =10min", "<bmin");
    }

    /**
     * <p>
     * beginningPrev_beginning.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void beginningPrev_beginning()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=bt =1hours =9min", "<bmin");
    }

    /**
     * <p>
     * beginningPrevOrThis_after.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void beginningPrevOrThis_after()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=bt =1hours =10min =1sec =2ms", "=bt =1hours =10min =0s =0n", "<=bmin", true);
    }

    /**
     * <p>
     * beginningPrevOrThis_beginning.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void beginningPrevOrThis_beginning()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=bt =1hours =10min", "<=bmin");
    }

    /**
     * <p>
     * endingNext_ending.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void endingNext_ending()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =1hours =10min", "=et =1hours =11min", ">emin");
    }

    /**
     * <p>
     * endingNext_notEnding.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void endingNext_notEnding()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=et =1hours =10min", ">emin");
    }

    /**
     * <p>
     * endingNextOrThis_ending.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void endingNextOrThis_ending()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =1hours =10min", "", ">=emin");
    }

    /**
     * <p>
     * endingNextOrThis_notEnding.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void endingNextOrThis_notEnding()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =1hours =10min =1sec", "=et =1hours =9min", "<=emin");
    }

    /**
     * <p>
     * endingPrev_ending.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void endingPrev_ending()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =1hours =10min", "=et =1hours =9min", "<emin");
    }

    /**
     * <p>
     * endingPrev_notEnding.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void endingPrev_notEnding()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=et =1hours =9min", "<emin");
    }

    /**
     * <p>
     * endingPrevOrThis_ending.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void endingPrevOrThis_ending()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =1hours =10min", "", "<=emin");
    }

    /**
     * <p>
     * endingPrevOrThis_notEnding.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void endingPrevOrThis_notEnding()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=et =1hours =10min =1sec", "=et =1hours =9min", "<=emin");
    }

    /**
     * <p>
     * greaterBeforeCurrentTimeResultsInFutureTime.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void greaterBeforeCurrentTimeResultsInFutureTime()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =12hours =30min", "=13hours =4min", ">4min");
    }

    /**
     * <p>
     * nextOrThisMinute_after.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void nextOrThisMinute_after()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10minutes", "=11min", ">=11minute");
    }

    /**
     * <p>
     * nextOrThisMinute_before.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void nextOrThisMinute_before()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10minutes", "=2hour =9min", ">=9minute");
    }

    /**
     * <p>
     * nextOrThisMinute_same.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void nextOrThisMinute_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10minutes", "", ">=10minute");
    }

    /**
     * <p>
     * prev_after.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void prev_after()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=0hour =11min", "<11minute");
    }

    /**
     * <p>
     * prev_before.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void prev_before()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=9minute", "<9minute");
    }

    /**
     * <p>
     * prev_same.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void prev_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=0hour", "<10minute");
    }

    /**
     * <p>
     * prevOrThis_after.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void prevOrThis_after()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=0hours =11min", "<=11minute");
    }

    /**
     * <p>
     * prevOrThis_before.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void prevOrThis_before()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "=9min", "<=9minute");
    }

    /**
     * <p>
     * prevOrThis_same.
     * </p>
     *
     * @throws java.lang.Exception if any.
     * @since 2.0.0
     */
    @Test
    public void prevOrThis_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=bt =1hours =10min", "", "<=10minute");
    }

}
