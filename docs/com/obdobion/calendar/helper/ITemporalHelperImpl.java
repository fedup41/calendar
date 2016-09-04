package com.obdobion.calendar.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>ITemporalHelperImpl interface.</p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public interface ITemporalHelperImpl
{
    /**
     * <p>getOutputDF.</p>
     *
     * @return a {@link java.time.format.DateTimeFormatter} object.
     */
    DateTimeFormatter getOutputDF();

    /**
     * <p>getOutputDTF.</p>
     *
     * @return a {@link java.time.format.DateTimeFormatter} object.
     */
    DateTimeFormatter getOutputDTF();

    /**
     * <p>getOutputSDF.</p>
     *
     * @return a {@link java.text.SimpleDateFormat} object.
     */
    SimpleDateFormat getOutputSDF();

    /**
     * <p>getOutputTF.</p>
     *
     * @return a {@link java.time.format.DateTimeFormatter} object.
     */
    DateTimeFormatter getOutputTF();

    /**
     * <p>parseWithPredefinedParsers.</p>
     *
     * @param valueStr a {@link java.lang.String} object.
     * @return a {@link java.time.LocalDateTime} object.
     * @throws java.text.ParseException if any.
     */
    LocalDateTime parseWithPredefinedParsers(final String valueStr) throws ParseException;
}
