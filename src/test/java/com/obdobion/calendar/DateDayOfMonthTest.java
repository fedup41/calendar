package com.obdobion.calendar;

import org.junit.Test;

/**
 * <p>DateDayOfMonthTest class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class DateDayOfMonthTest
{
    /**
     * <p>Constructor for DateDayOfMonthTest.</p>
     */
    public DateDayOfMonthTest()
    {

    }

    /**
     * <p>back12Months.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void back12Months()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =12M =31d =0ms", "=31d =12m =2010y", "-12month");
    }

    /**
     * <p>forward12Months.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void forward12Months()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2010y =12M =31d =0ms", "=31d =12m =2011y", "+12month");
    }

    /**
     * <p>from30to31backward.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void from30to31backward()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =11M =30d =0ms", "=10m =30d", "-1month");
    }

    /**
     * <p>from30to31forward.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void from30to31forward()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =11M =30d =0ms", "=12m =30d", "+1month");
    }

    /**
     * <p>from31to30backward.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void from31to30backward()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =12M =31d =0ms", "=11m =30d", "-1month");
    }

    /**
     * <p>from31to30forward.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void from31to30forward()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011y =10M =31d =0ms", "=11m =30d", "+1month");
    }

}
