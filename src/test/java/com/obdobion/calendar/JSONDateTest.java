package com.obdobion.calendar;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chris DeGreef
 * 
 */
public class JSONDateTest
{
    public JSONDateTest()
    {
    }

    @Test
    public void basicConstructor () throws Exception
    {
        Calendar cal = CalendarFactory.modifyJSON("2013-03-11T01:38:18.309-0500");
        Assert.assertEquals(
                "@2013year @3month @11day @1hour @38minute @18second @309millisecond",
                CalendarFactory.asFormula(cal));
        Assert.assertEquals("year", 2013, cal.get(Calendar.YEAR));
        Assert.assertEquals("month", Calendar.MARCH, cal.get(Calendar.MONTH));
        Assert.assertEquals("day", 11, cal.get(Calendar.DATE));
        Assert.assertEquals("hour", 1, cal.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals("minute", 38, cal.get(Calendar.MINUTE));
        Assert.assertEquals("second", 18, cal.get(Calendar.SECOND));
        Assert.assertEquals("millisecond", 309, cal.get(Calendar.MILLISECOND));
        Assert.assertEquals("timezone", -21600000, cal.get(Calendar.ZONE_OFFSET));

        Assert.assertEquals("JSON format", "2013-03-11T01:38:18.309-05", CalendarFactory.asJSON(cal));
    }
}
