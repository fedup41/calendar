package com.obdobion.calendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>DateWeekOfYearTest class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class DateWeekOfYearTest
{
    /**
     * <p>Constructor for DateWeekOfYearTest.</p>
     */
    public DateWeekOfYearTest()
    {

    }

    /**
     * <p>addBeginningWOY_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addBeginningWOY_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "+bweekofyear");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    /**
     * <p>addEndingWOY_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addEndingWOY_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "+eweekofyear");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    /**
     * <p>addWOY.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addWOY()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=11m =2d", "+2weekofyear");
    }

    /**
     * <p>atBeginningWOY.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atBeginningWOY()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms",
                "=2010y =12month =26d =btime",
                "=bweekofyear");
    }

    /**
     * <p>atEndingWOY.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atEndingWOY()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms",
                "=2011y =12month =25d =btime",
                "=eweekofyear");
    }

    /**
     * <p>atFirstWOY.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atFirstWOY()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=12m =29d =2010yr", "=1weekofyear");
    }

    /**
     * <p>atLastWOY.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atLastWOY()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=2011y =12month =28d", "=53weekofyear");
    }

    /**
     * <p>atZeroWOY.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atZeroWOY()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=12m =22d =2010yr", "=0weekofyear");
    }

    /**
     * <p>nextAbsoluteWOY_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextAbsoluteWOY_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "=2012y =10M =3d", ">40weekofyear");
    }

    /**
     * <p>nextAbsoluteWOY_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextAbsoluteWOY_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "=2011y =12M =7d", ">50weekofyear");
    }

    /**
     * <p>nextBeginningWOY_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextBeginningWOY_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =1M =2d =0ms", "=2012y =1month =1d =btime", ">bweekofyear");
    }

    /**
     * <p>nextEndingWOY_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextEndingWOY_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =12M =27d =0ms", "=2012y =12M =30d =btime", ">eweekofyear");
    }

    /**
     * <p>nextEndingWOY_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextEndingWOY_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =12M =1d =0ms", "=2011y =12M =25d =btime", ">eweekofyear");
    }

    /**
     * <p>nextOrThisAbsoluteWOY_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisAbsoluteWOY_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=2012y =1M =4d =0ms", ">=1weekofyear");
    }

    /**
     * <p>nextOrThisAbsoluteWOY_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisAbsoluteWOY_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=2011y =10M =19d =0ms", ">=43weekofyear");
    }

    /**
     * <p>nextOrThisAbsoluteWOY_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisAbsoluteWOY_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=2011y =10M =26d =0ms", ">=44weekofyear");
    }

    /**
     * <p>nextOrThisBeginningWOY_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisBeginningWOY_next()
            throws Exception
    {
        CalendarFactoryHelper
                .startExpectedComputed("=2011y =1M =2d =0ms", "=2012y =1month =1d =btime", ">=bweekofyear");
    }

    /**
     * <p>nextOrThisBeginningWOY_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisBeginningWOY_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =1M =1d =0ms",
                "=2010y =12month =26d =btime",
                ">=bweekofyear");
    }

    /**
     * <p>nextOrThisEndingWOY_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisEndingWOY_same()
            throws Exception
    {
        CalendarFactoryHelper
                .startExpectedComputed("=2011y =12M =27d =0ms", "=2011y =12M =25d =btime", ">=eweekofyear");
    }

    /**
     * <p>prevAbsoluteWOY_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevAbsoluteWOY_prev()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "=20d =10m =2010yr", "<43weekofyear");
    }

    /**
     * <p>prevAbsoluteWOY_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevAbsoluteWOY_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "=12d", "<42weekofyear");
    }

    /**
     * <p>prevBeginningWOY_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevBeginningWOY_prev()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2012y =1M =1d =0ms",
                "=26day =12m =2010year =btime",
                "<bweekofyear");
    }

    /**
     * <p>prevBeginningWOY_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevBeginningWOY_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2012y =11M =1d =0ms",
                "=1day =1m =2012year =btime",
                "<bweekofyear");
    }

    /**
     * <p>prevEndingWOY_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevEndingWOY_prev()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =12M =1d =0ms", "=2010y =12M =26d =btime", "<eweekofyear");
    }

    /**
     * <p>prevOrThisAbsoluteWOY_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisAbsoluteWOY_prev()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "=27d =10m =2010yr", "<=44weekofyear");
    }

    /**
     * <p>prevOrThisAbsoluteWOY_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisAbsoluteWOY_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "", "<=43weekofyear");
    }

    /**
     * <p>prevOrThisAbsoluteWOY_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisAbsoluteWOY_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "=12d", "<=42weekofyear");
    }

    /**
     * <p>prevOrThisBeginningWOY_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisBeginningWOY_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =1M =1d =0ms",
                "=26day =12m =2010year =btime",
                "<=bweekofyear");
    }

    /**
     * <p>prevOrThisEndingWOY_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisEndingWOY_prev()
            throws Exception
    {
        CalendarFactoryHelper
                .startExpectedComputed("=2011y =12M =31d =0ms", "=2011y =12M =25d =btime", "<=eweekofyear");
    }

    /**
     * <p>prevOrThisEndingWOY_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisEndingWOY_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =12M =1d =0ms", "=2010y =12M =26d =btime", "<=eweekofyear");
    }

    /**
     * <p>subtractEndingWOY_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtractEndingWOY_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "-eweekofyear");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    /**
     * <p>subtractWOY.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtractWOY()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=10m =5d", "-2weekofyear");
    }
}
