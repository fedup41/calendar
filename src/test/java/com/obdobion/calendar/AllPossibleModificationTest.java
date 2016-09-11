package com.obdobion.calendar;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

/**
 * = QTY is covered by the expected test case computations so there are not
 * specific tests for that.
 *
 * @author Chris DeGreef
 *
 */
public class AllPossibleModificationTest
{

    @Test
    public void atBegDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=0h =0mi =0s =0n", "=bd",
                true);
    }

    @Test
    public void atBegDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=4d", "=bdow", true);
    }

    @Test
    public void atBegHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=0mi =0s =0n", "=bh",
                true);
    }

    @Test
    public void atBegMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=0s =0n", "=bi", true);
    }

    @Test
    public void atBegMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=4m =1d =0h =0mi =0s =0n",
                "=bm", true);
    }

    @Test
    public void atBegMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", "=bms",
                    true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: AT", e.getMessage());
        }
    }

    @Test
    public void atBegNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=12n", "=bn", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: AT", e.getMessage());
        }
    }

    @Test
    public void atBegSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=0n", "=bs", true);
    }

    @Test
    public void atBegYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=1m =1d =0h =0mi =0s =0n",
                "=by", true);
    }

    @Test
    public void atEndDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=23h =59mi =59s =999999999n", "=ed", true);
    }

    @Test
    public void atEndDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =23h =59mi =59s =999999999n", "=edow", true);
    }

    @Test
    public void atEndHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =59i =59s =999999999n", "=59mi =59s =999999999n", "=eh", true);
    }

    @Test
    public void atEndMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=59s =999999999n", "=ei",
                true);
    }

    @Test
    public void atEndMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4m =30d =23h =59mi =59s =999999999n", "=em", true);
    }

    @Test
    public void atEndMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", "=ems",
                    true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: AT", e.getMessage());
        }
    }

    @Test
    public void atEndNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=12n", "=en", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: AT", e.getMessage());
        }
    }

    @Test
    public void atEndSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=999999999n", "=es", true);
    }

    @Test
    public void atEndYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12m =31d =23h =59mi =59s =999999999n", "=ey", true);
    }

    @Test
    public void atQtyDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=5d", "=2dow", true);
    }

    @Test
    public void minusBegDay()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-bd", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusBegDOW()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-bdow", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusBegHour()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-bh", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusBegMin()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-bi", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusBegMonth()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-bm", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusBegMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-bms", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusBegNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-bn", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusBegSec()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-bs", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusBegYear()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-by", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndDay()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-ed", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndDOW()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-edow", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndHour()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-eh", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndMin()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-ei", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndMonth()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-em", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-ems", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-en", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndSec()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-es", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusEndYear()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "-ey", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: SUBTRACT", e.getMessage());
        }
    }

    @Test
    public void minusQtyDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=8d", "-1d", true);
    }

    @Test
    public void minusQtyDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=8d", "-1dow", true);
    }

    @Test
    public void minusQtyHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=12h", "-1h", true);
    }

    @Test
    public void minusQtyMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=11i", "-1i", true);
    }

    @Test
    public void minusQtyMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=3m", "-1m", true);
    }

    @Test
    public void minusQtyMs()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=9s =999000011n", "-1ms", true);
    }

    @Test
    public void minusQtyNano()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=10n", "-1n", true);
    }

    @Test
    public void minusQtySecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=9s", "-1s", true);
    }

    @Test
    public void minusQtyYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=1959y", "-1y", true);
    }

    @Test
    public void nextBegDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =0h =0mi =0s =0n", ">bd", true);
    }

    @Test
    public void nextBegDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11d =0h =0mi =0s =0n", ">bdow", true);
    }

    @Test
    public void nextBegHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=14h =0mi =0s =0n", ">bh", true);
    }

    @Test
    public void nextBegMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=13mi =0s =0n", ">bi", true);
    }

    @Test
    public void nextBegMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=5m =1d =0h =0mi =0s =0n", ">bm", true);
    }

    @Test
    public void nextBegMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", ">bms", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void nextBegNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=12n", ">bn", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void nextBegSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11s =0n", ">bs", true);
    }

    @Test
    public void nextBegYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1961y =1m =1d =0h =0mi =0s =0n", ">by", true);
    }

    @Test
    public void nextEndDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =23h =59mi =59s =999999999n", ">ed", true);
    }

    @Test
    public void nextEndDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =23h =59mi =59s =999999999n", ">edow", true);
    }

    @Test
    public void nextEndHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=13h =59mi =59s =999999999n", ">eh", true);
    }

    @Test
    public void nextEndMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12mi =59s =999999999n", ">ei", true);
    }

    @Test
    public void nextEndMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4m =30d =23h =59mi =59s =999999999n", ">em", true);
    }

    @Test
    public void nextEndMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", ">ems", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void nextEndNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=12n", ">en", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void nextEndSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10s =999999999n", ">es", true);
    }

    @Test
    public void nextEndYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1960y =12m =31d =23h =59mi =59s =999999999n", ">ey", true);
    }

    @Test
    public void nextOrThisBegDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =0h =0mi =0s =0n", ">=bd", true);
    }

    @Test
    public void nextOrThisBegDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11d =0h =0mi =0s =0n", ">=bdow", true);
    }

    @Test
    public void nextOrThisBegHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=14h =0mi =0s =0n", ">=bh", true);
    }

    @Test
    public void nextOrThisBegMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=13mi =0s =0n", ">=bi", true);
    }

    @Test
    public void nextOrThisBegMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=5m =1d =0h =0mi =0s =0n", ">=bm", true);
    }

    @Test
    public void nextOrThisBegMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", ">=bms", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXTORTHIS", e.getMessage());
        }
    }

    @Test
    public void nextOrThisBegNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=12n", ">=bn", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXTORTHIS", e.getMessage());
        }
    }

    @Test
    public void nextOrThisBegSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11s =0n", ">=bs", true);
    }

    @Test
    public void nextOrThisBegYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1961y =1m =1d =0h =0mi =0s =0n", ">=by", true);
    }

    @Test
    public void nextOrThisEndDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=23h =59mi =59s =999999999n", ">=ed", true);
    }

    @Test
    public void nextOrThisEndDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =23h =59mi =59s =999999999n", ">=edow", true);
    }

    @Test
    public void nextOrThisEndHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=13h =59mi =59s =999999999n", ">=eh", true);
    }

    @Test
    public void nextOrThisEndMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12mi =59s =999999999n", ">=ei", true);
    }

    @Test
    public void nextOrThisEndMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4m =30d =23h =59mi =59s =999999999n", ">=em", true);
    }

    @Test
    public void nextOrThisEndMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", ">=ems", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXTORTHIS", e.getMessage());
        }
    }

    @Test
    public void nextOrThisEndNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=12n", ">=en", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXTORTHIS", e.getMessage());
        }
    }

    @Test
    public void nextOrThisEndSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10s =999999999n", ">=es", true);
    }

    @Test
    public void nextOrThisEndYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1960y =12m =31d =23h =59mi =59s =999999999n", ">=ey", true);
    }

    @Test
    public void nextOrThisQtyDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=5m =1d =0h =0mi =0s =0n", ">=1d", true);
    }

    @Test
    public void nextOrThisQtyDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11d =0h =0mi =0s =0n", ">=1dow", true);
    }

    @Test
    public void nextOrThisQtyHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =1h =0mi =0s =0n", ">=1h", true);
    }

    @Test
    public void nextOrThisQtyMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=14h =1mi =0s =0n", ">=1i", true);
    }

    @Test
    public void nextOrThisQtyMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1961y =3m =1d =0h =0mi =0s =0n", ">=3m", true);
    }

    @Test
    public void nextOrThisQtyMs()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10s =1000000n", ">=1ms", true);
    }

    @Test
    public void nextOrThisQtyNano()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11s =1n", ">=1n", true);
    }

    @Test
    public void nextOrThisQtySecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=13i =1s =0n", ">=1s", true);
    }

    @Test
    public void nextOrThisQtyYear()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1961y =1m =1d =0h =0mi =0s =0n", ">=1962y", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: NEXTORTHIS", e.getMessage());
        }
    }

    @Test
    public void nextQtyDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=5m =1d =0h =0mi =0s =0n", ">1d", true);
    }

    @Test
    public void nextQtyDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11d =0h =0mi =0s =0n", ">1dow", true);
    }

    @Test
    public void nextQtyHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =1h =0mi =0s =0n", ">1h", true);
    }

    @Test
    public void nextQtyMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=14h =1mi =0s =0n", ">1i", true);
    }

    @Test
    public void nextQtyMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1961y =2m =1d =0h =0mi =0s =0n", ">2m", true);
    }

    @Test
    public void nextQtyMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000000n", ">1ms", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void nextQtyNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=11s =1n", ">1n", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void nextQtySecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=13i =1s =0n", ">1s", true);
    }

    @Test
    public void nextQtyYear()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1961y =1m =1d =0h =0mi =0s =0n", ">1962y", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void plusBegDay()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+bd", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusBegDOW()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+bdow", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusBegHour()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+bh", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusBegMin()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+bi", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusBegMonth()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+bm", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusBegMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+bms", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusBegNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+bn", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusBegSec()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+bs", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusBegYear()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+by", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndDay()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+ed", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndDOW()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+edow", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndHour()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+eh", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndMin()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+ei", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndMonth()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+em", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+ems", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+en", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndSec()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+es", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusEndYear()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "", "+ey", true);
            Assert.fail("expected exception");
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: ADD", e.getMessage());
        }
    }

    @Test
    public void plusQtyDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=10d", "+1d", true);
    }

    @Test
    public void plusQtyDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=10d", "+1dow", true);
    }

    @Test
    public void plusQtyHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=14h", "+1h", true);
    }

    @Test
    public void plusQtyMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=13i", "+1i", true);
    }

    @Test
    public void plusQtyMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=5m", "+1m", true);
    }

    @Test
    public void plusQtyMs()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", "+1ms", true);
    }

    @Test
    public void plusQtyNano()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=12n", "+1n", true);
    }

    @Test
    public void plusQtySecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=11s", "+1s", true);
    }

    @Test
    public void plusQtyYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed("=1960y =04M =09d =13h =12i =10s =11n", "=1961y", "+1y", true);
    }

    @Test
    public void prevBegDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=0h =0mi =0s =0n", "<bd", true);
    }

    @Test
    public void prevBegDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4d =23h =59mi =59s =999999999n", "<bdow", true);
    }

    @Test
    public void prevBegHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=13h =0mi =0s =0n", "<bh", true);
    }

    @Test
    public void prevBegMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12mi =0s =0n", "<bi", true);
    }

    @Test
    public void prevBegMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4m =1d =0h =0mi =0s =0n", "<bm", true);
    }

    @Test
    public void prevBegMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", "<bms", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: PREV", e.getMessage());
        }
    }

    @Test
    public void prevBegNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=12n", "<bn", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: PREV", e.getMessage());
        }
    }

    @Test
    public void prevBegSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10s =0n", "<bs", true);
    }

    @Test
    public void prevBegYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1960y =1m =1d =0h =0mi =0s =0n", "<by", true);
    }

    @Test
    public void prevEndDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=8d =23h =59mi =59s =999999999n", "<ed", true);
    }

    @Test
    public void prevEndDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =23h =59mi =59s =999999999n", "<edow", true);
    }

    @Test
    public void prevEndHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12h =59mi =59s =999999999n", "<eh", true);
    }

    @Test
    public void prevEndMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11mi =59s =999999999n", "<ei", true);
    }

    @Test
    public void prevEndMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=3m =31d =23h =59mi =59s =999999999n", "<em", true);
    }

    @Test
    public void prevEndMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", "<ems", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: PREV", e.getMessage());
        }
    }

    @Test
    public void prevEndNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=12n", "<en", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: PREV", e.getMessage());
        }
    }

    @Test
    public void prevEndSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=9s =999999999n", "<es", true);
    }

    @Test
    public void prevEndYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1959y =12m =31d =23h =59mi =59s =999999999n", "<ey", true);
    }

    @Test
    public void prevOrThisBegDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=0h =0mi =0s =0n", "<=bd", true);
    }

    @Test
    public void prevOrThisBegDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4d =23h =59mi =59s =999999999n", "<=bdow", true);
    }

    @Test
    public void prevOrThisBegHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=13h =0mi =0s =0n", "<=bh", true);
    }

    @Test
    public void prevOrThisBegMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12mi =0s =0n", "<=bi", true);
    }

    @Test
    public void prevOrThisBegMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4m =1d =0h =0mi =0s =0n", "<=bm", true);
    }

    @Test
    public void prevOrThisBegMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", "<=bms", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: PREVORTHIS", e.getMessage());
        }
    }

    @Test
    public void prevOrThisBegNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=12n", "<=bn", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: PREVORTHIS", e.getMessage());
        }
    }

    @Test
    public void prevOrThisBegSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10s =0n", "<=bs", true);
    }

    @Test
    public void prevOrThisBegYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1960y =1m =1d =0h =0mi =0s =0n", "<=by", true);
    }

    @Test
    public void prevOrThisEndDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=8d =23h =59mi =59s =999999999n", "<=ed", true);
    }

    @Test
    public void prevOrThisEndDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=10d =23h =59mi =59s =999999999n", "<=edow", true);
    }

    @Test
    public void prevOrThisEndHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12h =59mi =59s =999999999n", "<=eh", true);
    }

    @Test
    public void prevOrThisEndMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=11mi =59s =999999999n", "<=ei", true);
    }

    @Test
    public void prevOrThisEndMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=3m =31d =23h =59mi =59s =999999999n", "<=em", true);
    }

    @Test
    public void prevOrThisEndMs()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1000011n", "<=ems", true);

        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: PREVORTHIS", e.getMessage());
        }
    }

    @Test
    public void prevOrThisEndNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=12n", "<=en", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: PREVORTHIS", e.getMessage());
        }
    }

    @Test
    public void prevOrThisEndSecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=9s =999999999n", "<=es", true);
    }

    @Test
    public void prevOrThisEndYear()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1959y =12m =31d =23h =59mi =59s =999999999n", "<=ey", true);
    }

    @Test
    public void prevOrThisQtyDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1d =23h =59mi =59s =999999999n", "<=1d", true);
    }

    @Test
    public void prevOrThisQtyDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4d =23h =59mi =59s =999999999n", "<=1dow", true);
    }

    @Test
    public void prevOrThisQtyHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=9d =1h =0mi =0s =0n", "<=1h", true);
    }

    @Test
    public void prevOrThisQtyMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1mi =0s =0n", "<=1i", true);
    }

    @Test
    public void prevOrThisQtyMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=3m =31d =23h =59mi =59s =999999999n", "<=3m", true);
    }

    @Test
    public void prevOrThisQtyMs()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=9s =1000000n", "<=1ms", true);
    }

    @Test
    public void prevOrThisQtyNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=10s =1n", "<=1n", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void prevOrThisQtySecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12i =1s =999999999n", "<=1s", true);
    }

    @Test
    public void prevOrThisQtyYear()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1961y =1m =1d =0h =0mi =0s =0n", "<=1962y", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: PREVORTHIS", e.getMessage());
        }
    }

    @Test
    public void prevQtyDay()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1d =23h =59mi =59s =999999999n", "<1d", true);
    }

    @Test
    public void prevQtyDOW()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=4d =23h =59mi =59s =999999999n", "<1dow", true);
    }

    @Test
    public void prevQtyHour()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=9d =1h =0mi =0s =0n", "<1h", true);
    }

    @Test
    public void prevQtyMinute()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=1mi =0s =0n", "<1i", true);
    }

    @Test
    public void prevQtyMonth()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=3m =31d =23h =59mi =59s =999999999n", "<3m", true);
    }

    @Test
    public void prevQtyMs()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=9s =1000000n", "<1ms", true);
    }

    @Test
    public void prevQtyNano()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=10s =1n", "<1n", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid quantity in data adjustment: NEXT", e.getMessage());
        }
    }

    @Test
    public void prevQtySecond()
            throws Exception
    {
        CalendarFactoryHelper.startExpectedComputed(
                "=1960y =04M =09d =13h =12i =10s =11n", "=12i =1s =999999999n", "<1s", true);
    }

    @Test
    public void prevQtyYear()
            throws Exception
    {
        try
        {
            CalendarFactoryHelper.startExpectedComputed(
                    "=1960y =04M =09d =13h =12i =10s =11n", "=1961y =1m =1d =0h =0mi =0s =0n", "<1962y", true);
        } catch (final ParseException e)
        {
            Assert.assertEquals("invalid direction in data adjustment: PREV", e.getMessage());
        }
    }

}
