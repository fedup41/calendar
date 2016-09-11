package com.obdobion.calendar;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

class DateAdjustment
{
    static final private DateTimeFormatter DebugTimeFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss.SSS");

    static final int                       MAXHOUR      = 23;
    static final int                       MAXMINUTE    = 59;
    static final int                       MAXSECOND    = 59;
    static final int                       MAXNANO      = 999999999;

    AdjustmentDirection                    direction;
    int                                    quantity;
    /*
     * Due to the direction being * and the qty being B or E. This will be the B
     * or E.
     */
    QuantityType                           quantityType;
    UnitOfMeasure                          unitOfMeasure;

    /**
     * <p>
     * Constructor for DateAdjustment.
     * </p>
     *
     * @param tokenValue a {@link java.lang.String} object.
     * @throws java.text.ParseException if any.
     */
    public DateAdjustment(final String tokenValue) throws ParseException
    {
        final int qtyStart = parseDirection(tokenValue);
        final int uomStart = parseQuantity(tokenValue, qtyStart);
        parseUnitOfMeasure(tokenValue, uomStart);
    }

    /**
     * <p>
     * adjust.
     * </p>
     *
     * @param ldt a {@link java.util.Calendar} object.
     * @throws java.text.ParseException if any.
     * @return a {@link java.time.LocalDateTime} object.
     */
    @SuppressWarnings("null")
    public LocalDateTime adjust(final LocalDateTime ldt) throws ParseException
    {
        StringBuilder debugStr = null;
        if (isInDebug())
        {
            debugStr = new StringBuilder();
            debugStr.append(ldt.format(DebugTimeFmt));
            debugStr.append(" ");
            debugStr.append(direction);
            debugStr.append(" ");
            debugStr.append(quantityType == QuantityType.ABSOLUTE
                    ? quantity
                    : quantityType);
            debugStr.append(" ");
            debugStr.append(unitOfMeasure);
        }
        LocalDateTime modifiedDateTime = ldt;
        switch (unitOfMeasure)
        {
            case TIME:
                modifiedDateTime = adjustTime(ldt, quantity);
                break;
            case NANOSECOND:
                modifiedDateTime = adjustNanos(ldt, quantity);
                break;
            case MILLISECOND:
                modifiedDateTime = adjustMillisecond(ldt, quantity);
                break;
            case SECOND:
                modifiedDateTime = adjustSecond(ldt, quantity);
                break;
            case MINUTE:
                modifiedDateTime = adjustMinute(ldt, quantity);
                break;
            case HOUR:
                modifiedDateTime = adjustHour(ldt, quantity);
                break;
            case DAY:
                modifiedDateTime = adjustDay(ldt, quantity);
                break;
            case DAYOFWEEK:
                modifiedDateTime = adjustDayOfWeek(ldt, quantity);
                break;
            case MONTH:
                modifiedDateTime = adjustMonth(ldt, quantity);
                break;
            case YEAR:
                modifiedDateTime = adjustYear(ldt, quantity);
                break;
            default:
                throw new ParseException("invalid unit of measure in data adjustment: " + unitOfMeasure, 0);
        }
        if (isInDebug())
        {
            debugStr.append(" = ");
            debugStr.append(modifiedDateTime.format(DebugTimeFmt));
            System.out.println(debugStr.toString());
        }
        return modifiedDateTime;
    }

    LocalDateTime adjustDay(final LocalDateTime ldt, final int qty) throws ParseException
    {
        LocalDateTime mldt = ldt;
        switch (quantityType)
        {
            case BEGINNING:

                switch (direction)
                {
                    case AT:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);
                    }
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);
                        if (ldt.getHour() != 0 || ldt.getMinute() != 0 || ldt.getSecond() != 0 || ldt.getNano() != 0)
                            return mldt.plusDays(1);
                        return mldt;
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);
                        return mldt.plusDays(1);
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);
                        return mldt;
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);
                        if (ldt.getHour() == 0 && ldt.getMinute() == 0 && ldt.getSecond() == 0 && ldt.getNano() == 0)
                            return mldt.minusDays(1);
                        return mldt;
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            case ENDING:
                switch (direction)
                {
                    case AT:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                    }
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (ldt.getHour() == MAXHOUR
                                && ldt.getMinute() == MAXMINUTE
                                && ldt.getSecond() == MAXSECOND
                                && ldt.getNano() == MAXNANO)
                            return mldt.plusDays(1);
                        return mldt;
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt.plusDays(1);
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (ldt.getHour() == MAXHOUR
                                && ldt.getMinute() == MAXMINUTE
                                && ldt.getSecond() == MAXSECOND
                                && ldt.getNano() == MAXNANO)
                            return mldt;
                        return mldt.minusDays(1);
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt.minusDays(1);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            default:
                switch (direction)
                {
                    case AT:
                        return mldt.withDayOfMonth(qty);
                    case ADD:
                        return mldt.plusDays(qty);
                    case SUBTRACT:
                        return mldt.minusDays(qty);
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);

                        if (qty < mldt.getDayOfMonth())
                        {
                            mldt = mldt.withDayOfMonth(qty);
                            return mldt.plusMonths(1);
                        }
                        return mldt.withDayOfMonth(qty);
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);

                        if (qty <= mldt.getDayOfMonth())
                        {
                            mldt = mldt.withDayOfMonth(qty);
                            return mldt.plusMonths(1);
                        }
                        return mldt.withDayOfMonth(qty);
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1,
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (qty > ldt.getDayOfMonth())
                        {
                            mldt = mldt.minusMonths(1);
                            mldt = mldt.withDayOfMonth(qty);
                            return mldt;
                        }
                        return mldt.withDayOfMonth(qty);
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (qty >= mldt.getDayOfMonth())
                        {
                            mldt = mldt.withDayOfMonth(qty);
                            return mldt.minusMonths(1);
                        }
                        return mldt.withDayOfMonth(qty);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
        }
    }

    LocalDateTime adjustDayOfWeek(final LocalDateTime ldt, final int qty) throws ParseException
    {
        LocalDateTime mldt = ldt;
        switch (quantityType)
        {
            case BEGINNING: // same as @bw or @bo
                switch (direction)
                {
                    case AT:
                        return ldt.with(DayOfWeek.MONDAY);
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);
                        if (ldt.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.MONDAY.getValue())
                            if (ldt.getHour() == 0
                                    && ldt.getMinute() == 0
                                    && ldt.getSecond() == 0
                                            & ldt.getNano() == 0)
                                return mldt;
                        mldt = mldt.plusWeeks(1);
                        return mldt.with(DayOfWeek.MONDAY);
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);
                        mldt = mldt.plusWeeks(1);
                        if (mldt.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.MONDAY.getValue())
                            return mldt;
                        return mldt.with(DayOfWeek.MONDAY);
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (ldt.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.MONDAY.getValue())
                            if (ldt.getHour() == MAXHOUR
                                    && ldt.getMinute() == MAXMINUTE
                                    && ldt.getSecond() == MAXSECOND
                                    && ldt.getNano() == MAXNANO)
                                return mldt;
                            else
                                return mldt.minusWeeks(1);
                        return mldt.with(DayOfWeek.MONDAY);
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (mldt.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.MONDAY.getValue())
                            mldt = mldt.minusWeeks(1);
                        return mldt.with(DayOfWeek.MONDAY);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            case ENDING: // same as @ew or @eo
                switch (direction)
                {
                    case AT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt.with(DayOfWeek.SUNDAY);
                    }
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt.with(DayOfWeek.SUNDAY);
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        mldt = mldt.with(DayOfWeek.SUNDAY);
                        if (ldt.getDayOfMonth() == mldt.getDayOfMonth()
                                && ldt.getHour() == MAXHOUR
                                && ldt.getMinute() == MAXMINUTE
                                && ldt.getSecond() == MAXSECOND
                                && ldt.getNano() == MAXNANO)
                            mldt = mldt.plusWeeks(1);
                        return mldt;
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt.with(DayOfWeek.SUNDAY);
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (mldt.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.SUNDAY.getValue())
                            mldt = mldt.minusWeeks(1);
                        return mldt.with(DayOfWeek.SUNDAY);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            default:
            {
                switch (direction)
                {
                    case AT:
                        return mldt.with(DayOfWeek.of(qty));
                    case ADD:
                        return mldt.plusDays(qty);
                    case SUBTRACT:
                        return mldt.minusDays(qty);
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);

                        if (qty < mldt.getDayOfWeek().getValue())
                        {
                            mldt = mldt.with(DayOfWeek.of(qty));
                            return mldt.plusDays(7);
                        }
                        return mldt.with(DayOfWeek.of(qty));
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                0, 0, 0, 0);

                        if (qty <= mldt.getDayOfWeek().getValue())
                        {
                            mldt = mldt.with(DayOfWeek.of(qty));
                            return mldt.plusDays(7);
                        }
                        return mldt.with(DayOfWeek.of(qty));
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (qty > mldt.getDayOfWeek().getValue())
                        {
                            mldt = mldt.with(DayOfWeek.of(qty));
                            return mldt.minusDays(7);
                        }
                        return mldt.with(DayOfWeek.of(qty));
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (qty >= mldt.getDayOfWeek().getValue())
                        {
                            mldt = mldt.with(DayOfWeek.of(qty));
                            return mldt.minusDays(7);
                        }
                        return mldt.with(DayOfWeek.of(qty));
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            }
        }
    }

    LocalDateTime adjustHour(final LocalDateTime ldt, final int qty) throws ParseException
    {
        LocalDateTime mldt = ldt;
        switch (quantityType)
        {
            case BEGINNING:

                switch (direction)
                {
                    case AT:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), 0, 0, 0);
                    }
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), 0, 0, 0);
                        if (ldt.getMinute() != 0 || ldt.getSecond() != 0 || ldt.getNano() != 0)
                            return mldt.plusHours(1);
                        return mldt;
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), 0, 0, 0);
                        return mldt.plusHours(1);
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), 0, 0, 0);
                        return mldt;
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), 0, 0, 0);
                        if (ldt.getMinute() == 0 && ldt.getSecond() == 0 && ldt.getNano() == 0)
                            return mldt.minusHours(1);
                        return mldt;
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            case ENDING:

                switch (direction)
                {
                    case AT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt;
                    }
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt;
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), MAXMINUTE, MAXSECOND, MAXNANO);
                        if (ldt.getMinute() != MAXMINUTE || ldt.getSecond() != MAXSECOND || ldt.getNano() != MAXNANO)
                            return mldt;
                        return mldt.plusHours(1);
                    }
                    case PREVORTHIS:
                    {
                        if (ldt.getMinute() == MAXMINUTE
                                && ldt.getSecond() == MAXSECOND
                                && ldt.getNano() == MAXNANO)
                            return mldt;
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt.minusHours(1);
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(), MAXMINUTE, MAXSECOND, MAXNANO);
                        return mldt.minusHours(1);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }

            default:
                switch (direction)
                {
                    case AT:
                        return ldt.withHour(qty);
                    case ADD:
                        return ldt.plusHours(qty);
                    case SUBTRACT:
                        return ldt.minusHours(qty);
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                qty, 0, 0, 0);
                        if (ldt.getHour() > qty)
                            return mldt.plusDays(1);
                        return mldt;
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                qty, 0, 0, 0);
                        if (ldt.getHour() >= qty)
                            return mldt.plusDays(1);
                        return mldt;
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                qty, 0, 0, 0);
                        if (ldt.getHour() < qty)
                            return mldt.minusDays(1);
                        return mldt;
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                qty, 0, 0, 0);
                        if (ldt.getHour() <= qty)
                            return mldt.minusDays(1);
                        return mldt;
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
        }
    }

    LocalDateTime adjustMillisecond(final LocalDateTime ldt, final int qty) throws ParseException
    {
        return adjustNanos(ldt, qty * 1000000);
    }

    LocalDateTime adjustMinute(final LocalDateTime ldt, final int qty) throws ParseException
    {
        LocalDateTime mldt = ldt;
        switch (quantityType)
        {
            case BEGINNING:

                switch (direction)
                {
                    case AT:
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                0, 0);

                    case NEXTORTHIS:
                    {
                        if (ldt.getSecond() != 0 || ldt.getNano() != 0)
                        {
                            mldt = LocalDateTime.of(
                                    ldt.getYear(),
                                    ldt.getMonth(),
                                    ldt.getDayOfMonth(),
                                    ldt.getHour(),
                                    ldt.getMinute(),
                                    0, 0);
                            return mldt.plusMinutes(1);
                        }
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                0, 0);
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(), 0, 0);
                        return mldt.plusMinutes(1);
                    }
                    case PREVORTHIS:
                    {
                        return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(),
                                ldt.getMinute(), 0, 0);
                    }
                    case PREV:
                    {
                        if (ldt.getSecond() != 0 || ldt.getNano() != 0)
                            return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(),
                                    ldt.getMinute(), 0, 0);
                        mldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(), 0, 0);
                        return mldt.minusMinutes(1);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }

            case ENDING:

                switch (direction)
                {
                    case AT:
                    {
                        return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(),
                                ldt.getMinute(), MAXSECOND, MAXNANO);
                    }
                    case NEXTORTHIS:
                    {
                        return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(),
                                ldt.getMinute(), MAXSECOND, MAXNANO);
                    }
                    case NEXT:
                    {
                        if (ldt.getSecond() != MAXSECOND || ldt.getNano() != MAXNANO)
                            return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(),
                                    ldt.getMinute(), MAXSECOND, MAXNANO);
                        mldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(), MAXSECOND, MAXNANO);
                        return mldt.plusMinutes(1);
                    }
                    case PREVORTHIS:
                    {
                        if (ldt.getSecond() != MAXSECOND || ldt.getNano() != MAXNANO)
                        {
                            mldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(),
                                    ldt.getDayOfMonth(),
                                    ldt.getHour(),
                                    ldt.getMinute(), MAXSECOND, MAXNANO);
                            return mldt.minusMinutes(1);
                        }
                        return LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(),
                                ldt.getMinute(), MAXSECOND, MAXNANO);
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(), MAXSECOND, MAXNANO);
                        return mldt.minusMinutes(1);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }

            default:
                switch (direction)
                {
                    case AT:
                        return ldt.withMinute(qty);
                    case ADD:
                        return ldt.plusMinutes(qty);
                    case SUBTRACT:
                        return ldt.minusMinutes(qty);
                    case NEXTORTHIS:
                    {
                        if (ldt.getMinute() > qty)
                            mldt = ldt.plusHours(1);
                        return LocalDateTime.of(mldt.getYear(), mldt.getMonth(), mldt.getDayOfMonth(), mldt.getHour(),
                                qty, 0, 0);
                    }
                    case NEXT:
                    {
                        if (ldt.getMinute() >= qty)
                            mldt = ldt.plusHours(1);
                        return LocalDateTime.of(mldt.getYear(), mldt.getMonth(), mldt.getDayOfMonth(), mldt.getHour(),
                                qty, 0, 0);
                    }
                    case PREVORTHIS:
                    {
                        if (ldt.getMinute() < qty)
                            mldt = ldt.minusHours(1);
                        return LocalDateTime.of(mldt.getYear(), mldt.getMonth(), mldt.getDayOfMonth(), mldt.getHour(),
                                qty, 0, 0);
                    }
                    case PREV:
                    {
                        if (ldt.getMinute() <= qty)
                            mldt = ldt.minusHours(1);
                        return LocalDateTime.of(mldt.getYear(), mldt.getMonth(), mldt.getDayOfMonth(), mldt.getHour(),
                                qty, 0, 0);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
        }
    }

    LocalDateTime adjustMonth(final LocalDateTime ldt, final int qty) throws ParseException
    {
        LocalDateTime mldt = ldt;
        switch (quantityType)
        {
            case BEGINNING:
                switch (direction)
                {
                    case AT:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1, 0, 0, 0, 0);
                    }
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1,
                                0, 0, 0, 0);
                        if (ldt.getDayOfMonth() != 1
                                || ldt.getHour() != 0
                                || ldt.getMinute() != 0
                                || ldt.getSecond() != 0
                                || ldt.getNano() != 0)
                            return mldt.plusMonths(1);
                        return mldt;
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1,
                                0, 0, 0, 0);
                        return mldt.plusMonths(1);
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1,
                                0, 0, 0, 0);
                        return mldt;
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1,
                                0, 0, 0, 0);
                        if (ldt.getDayOfMonth() == 1
                                && ldt.getHour() == 0
                                && ldt.getMinute() == 0
                                && ldt.getSecond() == 0
                                && ldt.getNano() == 0)
                            return mldt.minusMonths(1);
                        return mldt;
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            case ENDING:
            {
                switch (direction)
                {
                    case AT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                        mldt = mldt.plusMonths(1);
                        return mldt.minusDays(1);
                    }
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonthValue(),
                                1,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                        mldt = mldt.plusMonths(1);
                        return mldt.minusDays(1);
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonthValue(),
                                1,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                        mldt = mldt.plusMonths(1);
                        mldt = mldt.minusDays(1);

                        if (ldt.getDayOfMonth() == mldt.getDayOfMonth()
                                && ldt.getHour() == MAXHOUR
                                && ldt.getSecond() == MAXSECOND
                                && ldt.getNano() == MAXNANO)
                            return mldt.plusMonths(1);
                        return mldt;
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonthValue(),
                                1,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                        mldt = mldt.plusMonths(1);
                        mldt = mldt.minusDays(1);

                        if (ldt.getDayOfMonth() == mldt.getDayOfMonth()
                                && ldt.getHour() == MAXHOUR
                                && ldt.getMinute() == MAXMINUTE
                                && ldt.getSecond() == MAXSECOND
                                && ldt.getNano() == MAXNANO)
                            return ldt;
                        mldt = mldt.withDayOfMonth(1);
                        mldt = mldt.minusDays(1);
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonthValue(),
                                1,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                        return mldt.minusDays(1);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            }
            default:
            {
                switch (direction)
                {
                    case AT:
                        mldt = ldt.withMonth(qty);
                        /*
                         * It might be the case that we are moving from a month
                         * with more days and the day of the current date is
                         * greater than the number of days in the target month.
                         */
                        if (mldt.getMonthValue() != qty)
                            /*
                             * Move back to the end of the selected month.
                             */
                            return mldt.minusDays(mldt.getDayOfMonth());
                        return mldt;
                    case ADD:
                        return ldt.plusMonths(qty);
                    case SUBTRACT:
                        return ldt.minusMonths(qty);
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1,
                                0, 0, 0, 0);

                        if (qty < mldt.getMonthValue())
                        {
                            mldt = mldt.withMonth(qty);
                            return mldt.plusYears(1);
                        }
                        return mldt.withMonth(qty);
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1, 0, 0, 0, 0);

                        if (qty <= mldt.getMonthValue())
                        {
                            mldt = mldt.withMonth(qty);
                            return mldt.plusYears(1);
                        }
                        return mldt.withDayOfMonth(qty);
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1,
                                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (qty > mldt.getMonthValue())
                        {
                            mldt = mldt.withMonth(qty);
                            mldt = mldt.minusYears(1);
                            mldt = mldt.plusMonths(1);
                            mldt = mldt.minusDays(1);
                            return mldt;
                        }
                        mldt = mldt.withMonth(qty);
                        mldt = mldt.plusMonths(1);
                        mldt = mldt.minusDays(1);
                        return mldt;
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                1, MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
                        if (qty >= mldt.getMonthValue())
                        {
                            mldt = mldt.withMonth(qty);
                            mldt = mldt.minusYears(1);
                            mldt = mldt.plusMonths(1);
                            return mldt.minusDays(1);
                        }
                        mldt = mldt.withMonth(qty);
                        mldt = mldt.plusMonths(1);
                        return mldt.minusDays(1);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            }
        }
    }

    LocalDateTime adjustNanos(final LocalDateTime ldt, final int qty) throws ParseException
    {
        LocalDateTime mldt = ldt;
        switch (quantityType)
        {
            case BEGINNING:
                throw new ParseException("invalid quantity in data adjustment: " + direction, 0);
            case ENDING:
                throw new ParseException("invalid quantity in data adjustment: " + direction, 0);
            default:
            {
                switch (direction)
                {
                    case AT:
                        return ldt.withNano(qty);
                    case ADD:
                        return ldt.plusNanos(qty);
                    case SUBTRACT:
                        return ldt.minusNanos(qty);
                    case NEXTORTHIS:
                    {
                        if (qty < ldt.getNano())
                        {
                            mldt = ldt.withNano(qty);
                            return mldt.plusSeconds(1);
                        }
                        return mldt.withNano(qty);
                    }
                    case NEXT:
                    {
                        if (qty <= ldt.getNano())
                        {
                            mldt = ldt.withNano(qty);
                            return mldt.plusSeconds(1);
                        }
                        return mldt.withNano(qty);
                    }
                    case PREVORTHIS:
                    {
                        if (qty > ldt.getNano())
                        {
                            mldt = ldt.withNano(qty);
                            return mldt.minusSeconds(1);
                        }
                        return mldt.withNano(qty);
                    }
                    case PREV:
                    {
                        if (qty >= ldt.getNano())
                        {
                            mldt = ldt.withNano(qty);
                            return mldt.minusSeconds(1);
                        }
                        return mldt.withNano(qty);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            }
        }
    }

    LocalDateTime adjustSecond(final LocalDateTime ldt, final int qty) throws ParseException
    {
        LocalDateTime mldt = ldt;
        switch (quantityType)
        {
            case BEGINNING:

                switch (direction)
                {
                    case AT:
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                0);

                    case NEXTORTHIS:
                    {
                        if (ldt.getNano() != 0)
                        {
                            mldt = LocalDateTime.of(
                                    ldt.getYear(),
                                    ldt.getMonth(),
                                    ldt.getDayOfMonth(),
                                    ldt.getHour(),
                                    ldt.getMinute(),
                                    ldt.getSecond(), 0);
                            return mldt.plusSeconds(1);
                        }
                        return ldt;
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                0);
                        return mldt.plusSeconds(1);
                    }
                    case PREVORTHIS:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                0);
                    }
                    case PREV:
                    {
                        if (ldt.getNano() != 0)
                            return LocalDateTime.of(
                                    ldt.getYear(),
                                    ldt.getMonth(),
                                    ldt.getDayOfMonth(),
                                    ldt.getHour(),
                                    ldt.getMinute(),
                                    ldt.getSecond(),
                                    0);
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                0);
                        return mldt.minusSeconds(1);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            case ENDING:
                switch (direction)
                {
                    case AT:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                MAXNANO);
                    }
                    case NEXTORTHIS:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                MAXNANO);
                    }
                    case NEXT:
                    {
                        if (ldt.getNano() == MAXNANO)
                        {
                            mldt = LocalDateTime.of(
                                    ldt.getYear(),
                                    ldt.getMonth(),
                                    ldt.getDayOfMonth(),
                                    ldt.getHour(),
                                    ldt.getMinute(),
                                    ldt.getSecond(),
                                    MAXNANO);
                            return mldt.plusSeconds(1);
                        }
                        return LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                MAXNANO);
                    }
                    case PREVORTHIS:
                    {
                        if (ldt.getNano() == MAXNANO)
                            return ldt;

                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                MAXNANO);
                        return mldt.minusSeconds(1);
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                ldt.getMonth(),
                                ldt.getDayOfMonth(),
                                ldt.getHour(),
                                ldt.getMinute(),
                                ldt.getSecond(),
                                MAXNANO);
                        return mldt.minusSeconds(1);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            default:
            {
                switch (direction)
                {
                    case AT:
                        return mldt.withSecond(qty);
                    case ADD:
                        return mldt.plusSeconds(qty);
                    case SUBTRACT:
                        return mldt.minusSeconds(qty);
                    case NEXTORTHIS:
                    {
                        mldt = mldt.withNano(0);
                        if (qty < mldt.getSecond())
                        {
                            mldt = mldt.withSecond(qty);
                            return mldt.plusMinutes(1);
                        }
                        return mldt.withSecond(qty);
                    }
                    case NEXT:
                    {
                        mldt = mldt.withNano(0);
                        if (qty <= mldt.getSecond())
                        {
                            mldt = mldt.withSecond(qty);
                            return mldt.plusMinutes(1);
                        }
                        return mldt.withSecond(qty);
                    }
                    case PREVORTHIS:
                    {
                        mldt = mldt.withNano(MAXNANO);
                        if (qty > mldt.getSecond())
                        {
                            mldt = mldt.withSecond(qty);
                            return mldt.minusMinutes(1);
                        }
                        return mldt.withSecond(qty);
                    }
                    case PREV:
                    {
                        mldt = mldt.withNano(MAXNANO);
                        if (qty >= mldt.getSecond())
                        {
                            mldt = mldt.withSecond(qty);
                            return mldt.minusMinutes(1);
                        }
                        return mldt.withSecond(qty);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            }
        }
    }

    /**
     * @param ldt
     * @param qty
     * @throws ParseException
     */
    LocalDateTime adjustTime(final LocalDateTime ldt, final int qty) throws ParseException
    {
        if (direction == AdjustmentDirection.AT)
            switch (quantityType)
            {
                case BEGINNING:
                    return adjustToBeginningOfTime(ldt);
                case ENDING:
                    return adjustToEndOfTime(ldt);
                default:
                    throw new ParseException("invalid qty in data adjustment: " + direction, 0);
            }
        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
    }

    LocalDateTime adjustToBeginningOfTime(final LocalDateTime ldt)
    {
        return LocalDateTime.of(
                ldt.getYear(),
                ldt.getMonth(),
                ldt.getDayOfMonth(),
                0, 0, 0, 0);
    }

    LocalDateTime adjustToEndOfTime(final LocalDateTime ldt)
    {
        return LocalDateTime.of(
                ldt.getYear(),
                ldt.getMonth(),
                ldt.getDayOfMonth(),
                MAXHOUR, MAXMINUTE, MAXSECOND, MAXNANO);
    }

    LocalDateTime adjustYear(final LocalDateTime ldt, final int qty) throws ParseException
    {
        LocalDateTime mldt = ldt;
        switch (quantityType)
        {
            case BEGINNING:
                switch (direction)
                {
                    case AT:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                1, 1, 0, 0, 0, 0);
                    }
                    case NEXTORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                1, 1, 0, 0, 0, 0);
                        if (ldt.getMonthValue() != 1
                                || ldt.getDayOfMonth() != 1
                                || ldt.getHour() != 0
                                || ldt.getMinute() != 0
                                || ldt.getSecond() != 0
                                || ldt.getNano() != 0)
                            return mldt.plusYears(1);
                        return mldt;
                    }
                    case NEXT:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                1, 1, 0, 0, 0, 0);
                        return mldt.plusYears(1);
                    }
                    case PREVORTHIS:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                1, 1, 0, 0, 0, 0);
                        return mldt;
                    }
                    case PREV:
                    {
                        mldt = LocalDateTime.of(
                                ldt.getYear(),
                                1, 1, 0, 0, 0, 0);
                        if (ldt.getMonthValue() == 1
                                && ldt.getDayOfMonth() == 1
                                && ldt.getHour() == 0
                                && ldt.getMinute() == 0
                                && ldt.getSecond() == 0
                                && ldt.getNano() == 0)
                            return mldt.minusYears(1);
                        return mldt;
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            case ENDING:
            {
                switch (direction)
                {
                    case AT:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                12,
                                31,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                    }
                    case NEXTORTHIS:
                    {
                        return LocalDateTime.of(
                                ldt.getYear(),
                                12,
                                31,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                    }
                    case NEXT:
                    {
                        if (ldt.getMonthValue() != 12
                                || ldt.getDayOfMonth() != 31
                                || ldt.getHour() != MAXHOUR
                                || ldt.getSecond() != MAXSECOND
                                || ldt.getNano() != MAXNANO)
                            return LocalDateTime.of(
                                    ldt.getYear(),
                                    12,
                                    31,
                                    MAXHOUR,
                                    MAXMINUTE,
                                    MAXSECOND,
                                    MAXNANO);
                        return LocalDateTime.of(
                                ldt.getYear() + 1,
                                12,
                                31,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                    }
                    case PREVORTHIS:
                    {
                        if (ldt.getMonthValue() == 12
                                && ldt.getDayOfMonth() == 31
                                && ldt.getHour() == MAXHOUR
                                && ldt.getSecond() == MAXSECOND
                                && ldt.getNano() == MAXNANO)
                            return ldt;
                        return LocalDateTime.of(
                                ldt.getYear() - 1,
                                12,
                                31,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                    }
                    case PREV:
                    {
                        return LocalDateTime.of(
                                ldt.getYear() - 1,
                                12,
                                31,
                                MAXHOUR,
                                MAXMINUTE,
                                MAXSECOND,
                                MAXNANO);
                    }
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
            }

            default:
                switch (direction)
                {
                    case AT:
                        return ldt.withYear(qty);
                    case SUBTRACT:
                        return ldt.minusYears(qty);
                    case ADD:
                        return ldt.plusYears(qty);
                    default:
                        throw new ParseException("invalid direction in data adjustment: " + direction, 0);
                }
        }
    }

    boolean isEndOfThisYear(final LocalDateTime ldt)
    {
        return ldt.getDayOfMonth() == 31 && ldt.getMonth() == Month.DECEMBER;
    }

    /**
     * <p>
     * isInDebug.
     * </p>
     *
     * @return a boolean.
     */
    public boolean isInDebug()
    {
        return CalendarFactory.isInDebug();
    }

    int parseDirection(
            final String tokenValue)
                    throws ParseException
    {
        direction = AdjustmentDirection.find(tokenValue);
        return direction.size();
    }

    int parseEndOfNumber(
            final String tokenValue,
            final int startOfNumber)
    {
        for (int son = startOfNumber; son < tokenValue.length(); son++)
        {
            if (tokenValue.charAt(son) < '0')
                return son;
            if (tokenValue.charAt(son) > '9')
                return son;
        }
        return tokenValue.length();
    }

    int parseQuantity(
            final String tokenValue,
            final int qtyStart)
                    throws ParseException
    {
        int uomStart = 0;
        quantityType = QuantityType.ABSOLUTE;

        if (qtyStart >= tokenValue.length())
            throw new ParseException("Premature end of formula, qty expected: " + tokenValue,
                    qtyStart);

        uomStart = parseRelativeQuantity(tokenValue, qtyStart);
        if (uomStart == 0)
        {
            uomStart = parseEndOfNumber(tokenValue, qtyStart);
            if (uomStart == qtyStart)
                throw new ParseException("missing qty in " + tokenValue,
                        uomStart);
            quantity = Integer.parseInt(tokenValue.substring(qtyStart, uomStart));
        }
        return uomStart;
    }

    int parseRelativeQuantity(
            final String tokenValue,
            final int qtyStart)
    {
        if (tokenValue.charAt(qtyStart) == 'B' || tokenValue.charAt(qtyStart) == 'b')
        {
            quantityType = QuantityType.BEGINNING;
            return qtyStart + 1;
        } else if (tokenValue.charAt(qtyStart) == 'E' || tokenValue.charAt(qtyStart) == 'e')
        {
            quantityType = QuantityType.ENDING;
            return qtyStart + 1;
        }
        return 0;
    }

    void parseUnitOfMeasure(
            final String tokenValue,
            final int uomStart)
                    throws ParseException
    {
        final String uom = tokenValue.substring(uomStart).toLowerCase();
        if (uom == null || uom.trim().length() == 0)
            throw new ParseException("uom is required",
                    0);
        switch (uom.charAt(0))
        {
            case 't':
                unitOfMeasure = UnitOfMeasure.TIME;
                break;
            case 'y':
                unitOfMeasure = UnitOfMeasure.YEAR;
                break;
            case 'h':
                unitOfMeasure = UnitOfMeasure.HOUR;
                break;
            case 's':
                unitOfMeasure = UnitOfMeasure.SECOND;
                break;
            case 'l':
                unitOfMeasure = UnitOfMeasure.MILLISECOND;
                break;
            case 'n':
                unitOfMeasure = UnitOfMeasure.NANOSECOND;
                break;
            case 'i':
                unitOfMeasure = UnitOfMeasure.MINUTE;
                break;
            case 'a':
                unitOfMeasure = UnitOfMeasure.DAYOFWEEK;
                break;
            case 'm':
                if (uom.startsWith("mi"))
                    unitOfMeasure = UnitOfMeasure.MINUTE;
                else if (uom.equals("ms"))
                    unitOfMeasure = UnitOfMeasure.MILLISECOND;
                else
                    unitOfMeasure = UnitOfMeasure.MONTH;
                break;
            case 'd':
                if (uom.startsWith("dayofw") || uom.startsWith("dow"))
                    unitOfMeasure = UnitOfMeasure.DAYOFWEEK;
                else
                    unitOfMeasure = UnitOfMeasure.DAY;
                break;
            default:
                throw new ParseException("invalid uom: " + uom, 0);
        }
    }
}
