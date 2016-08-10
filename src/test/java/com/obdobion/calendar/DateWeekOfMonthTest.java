package com.obdobion.calendar;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>DateWeekOfMonthTest class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class DateWeekOfMonthTest
{
    /**
     * <p>Constructor for DateWeekOfMonthTest.</p>
     */
    public DateWeekOfMonthTest()
    {

    }

    /**
     * <p>addBeginningWOM_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addBeginningWOM_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "=9m =25d =btime", "+bweekofmonth");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    /**
     * <p>addEndingWOM_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addEndingWOM_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "=9m =25d =btime", "+eweekofmonth");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    /**
     * <p>addWOM.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void addWOM()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=2d =11m =btime", "+2weekofmonth");
    }

    /**
     * <p>atBeginningWOM.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atBeginningWOM()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "-1m =25d =btime", "=bweekofmonth");
    }

    /**
     * <p>atEndingWOM.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atEndingWOM()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=23d =btime", "=eweekofmonth");
    }

    /**
     * <p>atWOM.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void atWOM()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=5d =btime", "=2weekofmonth");
    }

    /**
     * <p>nextAbsoluteWOM_nextMonth.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextAbsoluteWOM_nextMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =16d =0ms", "+1m =20d =btime", ">4weekofmonth");
    }

    /**
     * <p>nextAbsoluteWOM_thisMonth.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextAbsoluteWOM_thisMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =10d =0ms", "=17d =btime", ">4weekofmonth");
    }

    /**
     * <p>nextBeginningWOM.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextBeginningWOM()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =30d", "=30d =btime =btime", ">bweekofmonth");
    }

    /**
     * <p>nextEndingWOM_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextEndingWOM_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =30d", "+1m =27d =btime", ">eweekofmonth");
    }

    /**
     * <p>nextEndingWOM_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextEndingWOM_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d", "=30d =btime", ">eweekofmonth");
    }

    /**
     * <p>nextOrThisAbsoluteWOM_nextMonth.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisAbsoluteWOM_nextMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =23d =0ms", "+1m =20d =btime", ">=4weekofmonth");
    }

    /**
     * <p>nextOrThisAbsoluteWOM_thisMonth.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisAbsoluteWOM_thisMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =10d =0ms", "=17d =btime", ">=4weekofmonth");
    }

    /**
     * <p>nextOrThisBeginningWOM_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisBeginningWOM_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =10d =0ms", "=30d =btime", ">=bweekofmonth");
    }

    /**
     * <p>nextOrThisBeginningWOM_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisBeginningWOM_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "=9M =25D =btime", ">=bweekofmonth");
    }

    /**
     * <p>nextOrThisEndingWOM_next.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisEndingWOM_next()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =10d", "=30d =btime", ">=eweekofmonth");
    }

    /**
     * <p>nextOrThisEndingWOM_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void nextOrThisEndingWOM_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =31d", "=30d =btime", ">=eweekofmonth");
    }

    /**
     * <p>prevAbsoluteWOM_prevMonth.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevAbsoluteWOM_prevMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =17d =0ms", "=9m =19d =btime", "<4weekofmonth");
    }

    /**
     * <p>prevAbsoluteWOM_thisMonth.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevAbsoluteWOM_thisMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =17d =0ms", "=10m =10d =btime", "<3weekofmonth");
    }

    /**
     * <p>prevBeginningWOM_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevBeginningWOM_prev()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "=8m =28d =btime", "<bweekofmonth");
    }

    /**
     * <p>prevBeginningWOM_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevBeginningWOM_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =23d =0ms", "=9m =25d =btime", "<bweekofmonth");
    }

    /**
     * <p>prevEndingWOM_this.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevEndingWOM_this()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =31d =0ms", "=9m =25d =btime", "<eweekofmonth");
    }

    /**
     * <p>prevOrThisAbsoluteWOM_prevMonth.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisAbsoluteWOM_prevMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =3d =0ms", "=9m =19d =btime", "<=4weekofmonth");
    }

    /**
     * <p>prevOrThisAbsoluteWOM_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisAbsoluteWOM_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =16d =0ms", "=10m =16d =btime", "<=4weekofmonth");
    }

    /**
     * <p>prevOrThisAbsoluteWOM_thisMonth.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisAbsoluteWOM_thisMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =23d =0ms", "=10m =16d =btime", "<=4weekofmonth");
    }

    /**
     * <p>prevOrThisBeginningWOM_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisBeginningWOM_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "=9m =25d =btime", "<=bweekofmonth");
    }

    /**
     * <p>prevOrThisEndingWOM_prev.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisEndingWOM_prev()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =23d =0ms", "=9m =25d =btime", "<=eweekofmonth");
    }

    /**
     * <p>prevOrThisEndingWOM_same.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void prevOrThisEndingWOM_same()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =31d =0ms", "=10m =30d =btime", "<=eweekofmonth");
    }

    /**
     * <p>subtractBeginningWOM_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtractBeginningWOM_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "=9m =25d =btime", "-bweekofmonth");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    /**
     * <p>subtractEndingWOM_ERROR.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtractEndingWOM_ERROR()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=2011y =10M =1d =0ms", "=9m =25d =btime", "-eweekofmonth");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    /**
     * <p>subtractWOM.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void subtractWOM()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =19d =0ms", "=5d =btime", "-2weekofmonth");
    }
}
