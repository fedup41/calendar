package com.obdobion.calendar;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * JSONDateTest class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class JSONDateTest
{
    /**
     * <p>
     * Constructor for JSONDateTest.
     * </p>
     */
    public JSONDateTest()
    {}

    /**
     * <p>
     * basicConstructor.
     * </p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void basicConstructor() throws Exception
    {
        final LocalDateTime ldt = CalendarFactory.modify("2013-03-11T01:38:18.309-0500");
        Assert.assertEquals(
                "=2013year =3month =11day =1hour =38minute =18second =309000000nanosecond",
                CalendarFactory.asFormula(ldt));
        Assert.assertEquals("year", 2013, ldt.getYear());
        Assert.assertEquals("month", Month.MARCH, ldt.getMonth());
        Assert.assertEquals("day", 11, ldt.getDayOfMonth());
        Assert.assertEquals("hour", 1, ldt.getHour());
        Assert.assertEquals("minute", 38, ldt.getMinute());
        Assert.assertEquals("second", 18, ldt.getSecond());
        Assert.assertEquals("nanosecond", 309000000, ldt.getNano());

        Assert.assertEquals("JSON format", "2013-03-11T01:38:18.309-05", CalendarFactory.asJSON(ldt));
    }
}
