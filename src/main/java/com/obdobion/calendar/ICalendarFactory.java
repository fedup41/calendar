package com.obdobion.calendar;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public interface ICalendarFactory
{
    String asFormula (final Calendar calendar);

    Calendar atImpl (long milliseconds);

    Calendar modifyImpl (Calendar startingDate, String... adjustmentsArray) throws ParseException;

    Calendar modifyImpl (Date startingDate, String... adjustmentsArray) throws ParseException;

    Calendar modifyImpl (long startingMilliseconds, String... adjustmentsArray) throws ParseException;

    Calendar noTimeImpl (Calendar startingDate);

    Date noTimeImpl (Date startingDate);

    Calendar nowImpl (String... adjustmentsArray) throws ParseException;

    void setBusinessDateImpl (Calendar businessDate);

    Calendar todayImpl (String... adjustmentsArray) throws ParseException;
}
