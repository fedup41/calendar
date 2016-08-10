package com.obdobion.calendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>DateParserErrorTest class.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.0.1
 */
public class DateParserErrorTest
{

    /**
     * <p>directionIsRequired.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void directionIsRequired()
            throws Exception
    {
        try
        {
            CalendarFactory.nowX("2010year");
            Assert.fail("should have been exception");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid direction: 2010year", e.getMessage());
        }

    }

    /**
     * <p>invalidUOM.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void invalidUOM()
            throws Exception
    {
        try
        {
            CalendarFactory.nowX("+1xx");
            Assert.fail("should have been exception");
        } catch (final Exception e)
        {
            Assert.assertEquals("invalid uom: xx", e.getMessage());
        }

    }

    /**
     * <p>missingQTY.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void missingQTY()
            throws Exception
    {
        try
        {
            CalendarFactory.nowX("+xx");
            Assert.fail("should have been exception");
        } catch (final Exception e)
        {
            Assert.assertEquals("missing qty in +xx", e.getMessage());
        }

    }

    /**
     * <p>spaceForQty.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void spaceForQty()
            throws Exception
    {
        try
        {
            CalendarFactory.nowX("+ d");
            Assert.fail("should have been exception");
        } catch (final Exception e)
        {
            Assert.assertEquals("Premature end of formula, qty expected: +", e.getMessage());
        }

    }

    /**
     * <p>uomIsRequired.</p>
     *
     * @throws java.lang.Exception if any.
     */
    @Test
    public void uomIsRequired()
            throws Exception
    {
        try
        {
            CalendarFactory.nowX("=2010");
            Assert.fail("should have been exception");
        } catch (final Exception e)
        {
            Assert.assertEquals("uom is required", e.getMessage());
        }

    }

}
