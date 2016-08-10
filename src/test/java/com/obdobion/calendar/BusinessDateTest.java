package com.obdobion.calendar;

import org.junit.Test;

/**
 * <p>BusinessDateTest class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class BusinessDateTest
{
    /**
     * <p>Constructor for BusinessDateTest.</p>
     */
    public BusinessDateTest()
    {
    }

    /**
     * <p>largeNegative.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void largeNegative()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011year =1month =1day =btime",
                "=2011year =1month =1day =btime",
                "=btime");
    }

    /**
     * <p>largePositive.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void largePositive()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2111year =1month =1day =btime",
                "=2111year =1month =1day =btime",
                "=btime");
    }

    /**
     * <p>smallNegative.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void smallNegative()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011year =10month =20day =btime",
                "=2011year =10month =20day =btime",
                "=btime");
    }

    /**
     * <p>smallPositive.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void smallPositive()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=2011year =12month =31day =btime",
                "=2011year =12month =31day =btime",
                "=btime");
    }

}
