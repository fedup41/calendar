package com.obdobion.calendar;

import java.text.ParseException;
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

    /**
     * <p>getOutputDF.</p>
     *
     * @return a {@link java.time.format.DateTimeFormatter} object.
     */
    static public DateTimeFormatter getOutputDF()
    {
        return getDelegate().getOutputDF();
    }

    /**
     * <p>getOutputDTF.</p>
     *
     * @return a {@link java.time.format.DateTimeFormatter} object.
     */
    static public DateTimeFormatter getOutputDTF()
    {
        return getDelegate().getOutputDTF();
    }

    /**
     * <p>getOutputSDF.</p>
     *
     * @return a {@link java.text.SimpleDateFormat} object.
     */
    static public SimpleDateFormat getOutputSDF()
    {
        return getDelegate().getOutputSDF();
    }

    /**
     * <p>getOutputTF.</p>
     *
     * @return a {@link java.time.format.DateTimeFormatter} object.
     */
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
     * @throws java.text.ParseException if any.
     */
    static public LocalDateTime parseWithPredefinedParsers(final String valueStr) throws ParseException
    {
        return getDelegate().parseWithPredefinedParsers(valueStr);
    }

    /**
     * <p>Setter for the field <code>delegate</code>.</p>
     *
     * @param newImpl a {@link com.obdobion.calendar.helper.ITemporalHelperImpl} object.
     * @return a {@link com.obdobion.calendar.helper.ITemporalHelperImpl} object.
     */
    static public ITemporalHelperImpl setDelegate(final ITemporalHelperImpl newImpl)
    {
        final ITemporalHelperImpl previousImpl = delegate;
        delegate = newImpl;
        return previousImpl;
    }
}
