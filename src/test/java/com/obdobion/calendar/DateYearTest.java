package com.obdobion.calendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>DateYearTest class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class DateYearTest
{
    /**
     * <p>Constructor for DateYearTest.</p>
     */
    public DateYearTest()
    {

    }

    /**
     * <p>absoluteBeginningYear_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void absoluteBeginningYear_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2d =1M =2011y =btime", "=1d =1m =2011year", "=byear");
    }

    /**
     * <p>absoluteEndYear_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void absoluteEndYear_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2d =1M =2011y =btime", "=12m =31d =2011year =etime", "=eyear");
    }

    /**
     * <p>addBeginningYear_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addBeginningYear_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "+byear");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    /**
     * <p>addEndingYear_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addEndingYear_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "+eyear");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    /**
     * <p>addYear.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "=2013y", "+2year");
    }

    /**
     * <p>nextBeginningYear.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextBeginningYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1d =1M =2011y =btime", "=1d =1m =2012y", ">byear");
    }

    /**
     * <p>nextEndOfYear.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextEndOfYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1d =1M =2011y =btime", "=12m =31d =2012y =etime", ">eyear");
    }

    /**
     * <p>nextOrThisBeginningYear_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisBeginningYear_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2d =1M =2011y =btime", "=1d =1m =2012year", ">=byear");
    }

    /**
     * <p>nextOrThisBeginningYear_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisBeginningYear_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1d =1M =2011y =btime", "", ">=byear");
    }

    /**
     * <p>nextOrThisEndOfYear.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisEndOfYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=12M =31d =2011y", "=etime", ">=eyear");
    }

    /**
     * <p>nextOrThisYear_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisYear_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", ">=1year");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: NEXTORTHIS", e.getMessage());
        }
    }

    /**
     * <p>nextThisYear_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextThisYear_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", ">1year");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: NEXT", e.getMessage());
        }
    }

    /**
     * <p>prevBeginningYear.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevBeginningYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2d =1M =2011y =btime", "=1d =2010y", "<byear");
    }

    /**
     * <p>prevEndOfYear_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevEndOfYear_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=12M =30d =2011y", "=31d -1year =etime", "<eyear");
    }

    /**
     * <p>prevEndOfYear_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevEndOfYear_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=12M =31d =2011y", "-1year =etime", "<eyear");
    }

    /**
     * <p>prevOrThisBeginningYear_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisBeginningYear_prev()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2d =1M =2011y =btime", "=1d =1m =2011year", "<=byear");
    }

    /**
     * <p>prevOrThisEndOfYear_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisEndOfYear_prev()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=12M =30d =2011y", "=31d -1year =etime", "<=eyear");
    }

    /**
     * <p>prevOrThisEndOfYear_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisEndOfYear_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=12M =31d =2011y", "=etime", "<=eyear");
    }

    /**
     * <p>prevOrThisYear_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisYear_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "<=1year");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: PREVORTHIS", e.getMessage());
        }
    }

    /**
     * <p>prevThisYear_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevThisYear_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "<1year");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: PREV", e.getMessage());
        }
    }

    /**
     * <p>subtractBeginningYear_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtractBeginningYear_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "-byear");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    /**
     * <p>subtractEndingYear_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtractEndingYear_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "", "-eyear");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    /**
     * <p>subtractYear.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtractYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d", "=2009y", "-2year");
    }
}
