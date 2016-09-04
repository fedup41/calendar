package com.obdobion.calendar.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface ITemporalHelperImpl
{
    DateTimeFormatter getOutputDF();

    DateTimeFormatter getOutputDTF();

    SimpleDateFormat getOutputSDF();

    DateTimeFormatter getOutputTF();

    LocalDateTime parseWithPredefinedParsers(final String valueStr) throws ParseException;
}