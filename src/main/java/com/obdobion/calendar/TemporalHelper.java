package com.obdobion.calendar;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.obdobion.calendar.helper.ITemporalHelperImpl;
import com.obdobion.calendar.helper.TemporalHelperUSImpl;

/**
 * <p>
 * TemporalHelper class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public class TemporalHelper
{
    static ITemporalHelperImpl delegate;

    static ITemporalHelperImpl getDelegate()
    {
        if (delegate == null)
            delegate = new TemporalHelperUSImpl();
        return delegate;
    }

    static public DateTimeFormatter getOutputDF()
    {
        return getDelegate().getOutputDF();
    }

    static public DateTimeFormatter getOutputDTF()
    {
        return getDelegate().getOutputDTF();
    }

    static public SimpleDateFormat getOutputSDF()
    {
        return getDelegate().getOutputSDF();
    }

    static public DateTimeFormatter getOutputTF()
    {
        return getDelegate().getOutputTF();
    }

    /**
     * <p>
     * parseWithPredefinedParsers.
     * </p>
     *
     * @param valueStr a {@link java.lang.String} object.
     * @return a {@link java.util.Date} object.
     * @throws java.lang.Exception if any.
     */
    static public LocalDateTime parseWithPredefinedParsers(final String valueStr) throws Exception
    {
        return getDelegate().parseWithPredefinedParsers(valueStr);
    }

    static public ITemporalHelperImpl setDelegate(final ITemporalHelperImpl newImpl)
    {
        final ITemporalHelperImpl previousImpl = delegate;
        delegate = newImpl;
        return previousImpl;
    }
}
