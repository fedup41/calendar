package com.obdobion.calendar;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p>
 * MasterSuite class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 2.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({
        BusinessDateTest.class,
        DateDayOfMonthTest.class,
        DateDayOfWeekTest.class,
        DateHourTest.class,
        DateMinuteTest.class,
        DateParserErrorTest.class,
        DateYearTest.class,
        JSONDateTest.class })
public class MasterSuite
{

}
