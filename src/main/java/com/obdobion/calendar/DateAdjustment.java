package com.obdobion.calendar;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class DateAdjustment
{
    static final private Format DebugTimeFmt = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss.SSS");
    AdjustmentDirection         direction;

    int                         quantity;

    /*
     * Due to the direction being * and the qty being B or E. This will be the B
     * or E.
     */
    QuantityType                quantityType;

    UnitOfMeasure               unitOfMeasure;

    /**
     * <p>Constructor for DateAdjustment.</p>
     *
     * @param tokenValue a {@link java.lang.String} object.
     * @throws java.text.ParseException if any.
     */
    public DateAdjustment(
            final String tokenValue)
                    throws ParseException
    {
        final int qtyStart = parseDirection(tokenValue);
        final int uomStart = parseQuantity(tokenValue, qtyStart);
        parseUnitOfMeasure(tokenValue, uomStart);
    }

    /**
     * <p>adjust.</p>
     *
     * @param cal a {@link java.util.Calendar} object.
     * @throws java.text.ParseException if any.
     */
    @SuppressWarnings("null")
    public void adjust(
            final Calendar cal)
                    throws ParseException
    {
        StringBuilder debugStr = null;
        if (isInDebug())
        {
            debugStr = new StringBuilder();
            debugStr.append(DebugTimeFmt.format(cal.getTime()));
            debugStr.append(" ");
            debugStr.append(direction);
            debugStr.append(" ");
            debugStr.append(quantityType == QuantityType.ABSOLUTE
                    ? quantity
                    : quantityType);
            debugStr.append(" ");
            debugStr.append(unitOfMeasure);
        }

        int qty = quantity;
        if (direction == AdjustmentDirection.SUBTRACT)
            qty = 0 - qty;

        switch (unitOfMeasure)
        {
        case TIME:
            adjustTime(cal, qty);
            break;
        case MILLISECOND:
            adjustMillisecond(cal, qty);
            break;
        case SECOND:
            adjustSecond(cal, qty);
            break;
        case MINUTE:
            adjustMinute(cal, qty);
            break;
        case HOUR:
            adjustHour(cal, qty);
            break;
        case DAY:
            adjustDay(cal, qty);
            break;
        case DAYOFWEEK:
            adjustDayOfWeek(cal, qty);
            break;
        case WEEKOFYEAR:
            adjustWeekOfYear(cal, qty);
            break;
        case WEEKOFMONTH:
            adjustWeekOfMonth(cal, qty);
            break;
        case MONTH:
            adjustMonth(cal, qty);
            break;
        case YEAR:
            adjustYear(cal, qty);
            break;
        default:
            throw new ParseException("invalid unit of measure in data adjustment: " + unitOfMeasure,
                    0);
        }

        if (isInDebug())
        {
            debugStr.append(" = ");
            debugStr.append(DebugTimeFmt.format(cal.getTime()));
            System.out.println(debugStr.toString());
        }
    }

    void adjustDay(
            final Calendar cal,
            final int qty)
    {
        switch (quantityType)
        {
        case BEGINNING:
            adjustToBeginningOfTime(cal);
            break;
        case ENDING:
            adjustToEndOfTime(cal);
            break;
        default:
            if (direction == AdjustmentDirection.AT)
                cal.set(Calendar.DATE, qty);
            else
                cal.add(Calendar.DATE, qty);
        }
    }

    void adjustDayOfWeek(
            final Calendar cal,
            final int qty)
                    throws ParseException
    {
        switch (quantityType)
        {
        case BEGINNING: // same as @bw or @bo
            switch (direction)
            {
            case AT:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
            case NEXTORTHIS:
                adjustToBeginningOfTime(cal);
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                    break;
                cal.add(Calendar.WEEK_OF_MONTH, 1);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
            case NEXT:
                cal.add(Calendar.WEEK_OF_MONTH, 1);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                adjustToBeginningOfTime(cal);
                break;
            case PREVORTHIS:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                adjustToBeginningOfTime(cal);
                break;
            case PREV:
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                    cal.add(Calendar.WEEK_OF_MONTH, -1);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                adjustToBeginningOfTime(cal);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            break;
        case ENDING: // same as @ew or @eo
            switch (direction)
            {
            case AT:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
            case NEXTORTHIS:
                adjustToEndOfTime(cal);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                break;
            case NEXT:
                cal.add(Calendar.WEEK_OF_MONTH, 1);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                adjustToEndOfTime(cal);
                break;
            case PREVORTHIS:
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                adjustToEndOfTime(cal);
                break;
            case PREV:
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                    cal.add(Calendar.WEEK_OF_MONTH, -1);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                adjustToEndOfTime(cal);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            break;
        default:
            switch (direction)
            {
            case AT:
                cal.set(Calendar.DAY_OF_WEEK, qty);
                break;
            case NEXTORTHIS:
                if (cal.get(Calendar.DAY_OF_WEEK) == qty)
                    break;
                if (cal.get(Calendar.DAY_OF_WEEK) > qty)
                    cal.add(Calendar.WEEK_OF_MONTH, 1);
                break;
            case NEXT:
                if (cal.get(Calendar.DAY_OF_WEEK) >= qty)
                    cal.add(Calendar.WEEK_OF_MONTH, 1);
                break;
            case PREVORTHIS:
                if (cal.get(Calendar.DAY_OF_WEEK) == qty)
                    break;
                if (cal.get(Calendar.DAY_OF_WEEK) < qty)
                    cal.add(Calendar.WEEK_OF_MONTH, -1);
                break;
            case PREV:
                if (cal.get(Calendar.DAY_OF_WEEK) <= qty)
                    cal.add(Calendar.WEEK_OF_MONTH, -1);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            cal.set(Calendar.DAY_OF_WEEK, qty);
        }
    }

    void adjustHour(
            final Calendar cal,
            final int qty)
                    throws ParseException
    {
        switch (quantityType)
        {
        case BEGINNING:

            switch (direction)
            {
            case AT:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                adjustToBeginningOfTime(cal);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                break;
            }
            case NEXTORTHIS:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                if (cal.get(Calendar.MINUTE) != 0
                        || cal.get(Calendar.SECOND) != 0
                        || cal.get(Calendar.MILLISECOND) != 0)
                {
                    adjustToBeginningOfTime(cal);
                    cal.set(Calendar.HOUR_OF_DAY, hour + 1);
                }
                break;
            }
            case NEXT:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                adjustToBeginningOfTime(cal);
                cal.set(Calendar.HOUR_OF_DAY, hour + 1);
                break;
            }
            case PREVORTHIS:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                adjustToBeginningOfTime(cal);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                break;
            }
            case PREV:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);

                if (cal.get(Calendar.MINUTE) != 0
                        || cal.get(Calendar.SECOND) != 0
                        || cal.get(Calendar.MILLISECOND) != 0)
                {
                    adjustToBeginningOfTime(cal);
                    cal.set(Calendar.HOUR_OF_DAY, hour);
                } else
                {
                    adjustToBeginningOfTime(cal);
                    cal.set(Calendar.HOUR_OF_DAY, hour - 1);
                }
                break;
            }
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }

            break;
        case ENDING:

            switch (direction)
            {
            case AT:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                adjustToEndOfTime(cal);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                break;
            }
            case NEXTORTHIS:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                adjustToEndOfTime(cal);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                break;
            }
            case NEXT:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                if (cal.get(Calendar.MINUTE) != 59
                        || cal.get(Calendar.SECOND) != 59
                        || cal.get(Calendar.MILLISECOND) != 999)
                {
                    adjustToEndOfTime(cal);
                    cal.set(Calendar.HOUR_OF_DAY, hour);
                } else
                {
                    adjustToEndOfTime(cal);
                    cal.set(Calendar.HOUR_OF_DAY, hour + 1);
                }
                break;
            }
            case PREVORTHIS:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);

                if (cal.get(Calendar.MINUTE) != 0
                        || cal.get(Calendar.SECOND) != 0
                        || cal.get(Calendar.MILLISECOND) != 0)
                {
                    adjustToEndOfTime(cal);
                    cal.set(Calendar.HOUR_OF_DAY, hour);
                } else
                {
                    adjustToEndOfTime(cal);
                    cal.set(Calendar.HOUR_OF_DAY, hour - 1);
                }
                break;
            }
            case PREV:
            {
                final int hour = cal.get(Calendar.HOUR_OF_DAY);
                adjustToEndOfTime(cal);
                cal.set(Calendar.HOUR_OF_DAY, hour - 1);
                break;
            }
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            break;

        default:
            switch (direction)
            {
            case AT:
                cal.set(Calendar.HOUR_OF_DAY, qty);
                break;
            case ADD:
                cal.add(Calendar.HOUR_OF_DAY, qty);
                break;
            case SUBTRACT:
                cal.add(Calendar.HOUR_OF_DAY, qty);
                break;
            case NEXTORTHIS:
                if (cal.get(Calendar.HOUR_OF_DAY) > qty)
                    cal.add(Calendar.DATE, 1);
                cal.set(Calendar.HOUR_OF_DAY, qty);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            case NEXT:
                if (cal.get(Calendar.HOUR_OF_DAY) >= qty)
                    cal.add(Calendar.DATE, 1);
                cal.set(Calendar.HOUR_OF_DAY, qty);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            case PREVORTHIS:
                if (cal.get(Calendar.HOUR_OF_DAY) < qty)
                    cal.add(Calendar.DATE, -1);
                cal.set(Calendar.HOUR_OF_DAY, qty);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            case PREV:
                if (cal.get(Calendar.HOUR_OF_DAY) <= qty)
                    cal.add(Calendar.DATE, -1);
                cal.set(Calendar.HOUR_OF_DAY, qty);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
        }
    }

    void adjustMillisecond(
            final Calendar cal,
            final int qty)
    {
        if (direction == AdjustmentDirection.AT)
            cal.set(Calendar.MILLISECOND, qty);
        else
            cal.add(Calendar.MILLISECOND, qty);
    }

    void adjustMinute(
            final Calendar cal,
            final int qty)
                    throws ParseException
    {
        switch (quantityType)
        {
        case BEGINNING:

            switch (direction)
            {
            case AT:
            {
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            }
            case NEXTORTHIS:
            {
                if (cal.get(Calendar.SECOND) != 0 || cal.get(Calendar.MILLISECOND) != 0)
                {
                    cal.add(Calendar.MINUTE, 1);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                }
                break;
            }
            case NEXT:
            {
                cal.add(Calendar.MINUTE, 1);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            }
            case PREVORTHIS:
            {
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            }
            case PREV:
            {
                if (cal.get(Calendar.SECOND) != 0 || cal.get(Calendar.MILLISECOND) != 0)
                {
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                } else
                {
                    cal.add(Calendar.MINUTE, -1);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                }
                break;
            }
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }

            break;
        case ENDING:

            switch (direction)
            {
            case AT:
            {
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 999);
                break;
            }
            case NEXTORTHIS:
            {
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 999);
                break;
            }
            case NEXT:
            {
                if (cal.get(Calendar.SECOND) != 59 || cal.get(Calendar.MILLISECOND) != 999)
                {
                    cal.set(Calendar.SECOND, 59);
                    cal.set(Calendar.MILLISECOND, 999);
                } else
                {
                    cal.add(Calendar.MINUTE, 1);
                    cal.set(Calendar.SECOND, 59);
                    cal.set(Calendar.MILLISECOND, 999);
                }
                break;
            }
            case PREVORTHIS:
            {
                if (cal.get(Calendar.SECOND) != 59 || cal.get(Calendar.MILLISECOND) != 999)
                {
                    cal.add(Calendar.MINUTE, -1);
                    cal.set(Calendar.SECOND, 59);
                    cal.set(Calendar.MILLISECOND, 999);
                }
                break;
            }
            case PREV:
            {
                cal.add(Calendar.MINUTE, -1);
                cal.set(Calendar.SECOND, 59);
                cal.set(Calendar.MILLISECOND, 999);
                break;
            }
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            break;

        default:
            switch (direction)
            {
            case AT:
                cal.set(Calendar.MINUTE, qty);
                break;
            case ADD:
                cal.add(Calendar.MINUTE, qty);
                break;
            case SUBTRACT:
                cal.add(Calendar.MINUTE, qty);
                break;
            case NEXTORTHIS:
                if (cal.get(Calendar.MINUTE) > qty)
                    cal.add(Calendar.HOUR_OF_DAY, 1);
                cal.set(Calendar.MINUTE, qty);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            case NEXT:
                if (cal.get(Calendar.MINUTE) >= qty)
                    cal.add(Calendar.HOUR_OF_DAY, 1);
                cal.set(Calendar.MINUTE, qty);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            case PREVORTHIS:
                if (cal.get(Calendar.MINUTE) < qty)
                    cal.add(Calendar.HOUR_OF_DAY, -1);
                cal.set(Calendar.MINUTE, qty);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            case PREV:
                if (cal.get(Calendar.MINUTE) <= qty)
                    cal.add(Calendar.HOUR_OF_DAY, -1);
                cal.set(Calendar.MINUTE, qty);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
        }
    }

    void adjustMonth(
            final Calendar cal,
            final int qty)
    {
        switch (quantityType)
        {
        case BEGINNING:
            cal.set(Calendar.DATE, 1);
            adjustToBeginningOfTime(cal);
            break;
        case ENDING:
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DATE, 1);
            cal.add(Calendar.DATE, -1);
            adjustToEndOfTime(cal);
            break;
        default:
            if (direction == AdjustmentDirection.AT)
            {
                cal.set(Calendar.MONTH, qty - 1);
                /*
                 * It might be the case that we are moving from a month with
                 * more days and the day of the current date is greater than the
                 * number of days in the target month.
                 */
                if (cal.get(Calendar.MONTH) != (qty - 1))
                {
                    /*
                     * Move back to the end of the selected month.
                     */
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                }
            } else
                cal.add(Calendar.MONTH, qty);
        }
    }

    void adjustSecond(
            final Calendar cal,
            final int qty)
    {

        switch (quantityType)
        {
        case BEGINNING:
            cal.set(Calendar.MILLISECOND, 0);
            break;
        case ENDING:
            cal.set(Calendar.MILLISECOND, 999);
            break;
        default:
            if (direction == AdjustmentDirection.AT)
            {
                cal.set(Calendar.SECOND, qty);
            } else
                cal.add(Calendar.SECOND, qty);
        }
    }

    /**
     * @param cal
     * @param qty
     * @throws ParseException
     */
    void adjustTime(
            final Calendar cal,
            final int qty)
                    throws ParseException
    {
        if (direction == AdjustmentDirection.AT)

            switch (quantityType)
            {
            case BEGINNING:
                adjustToBeginningOfTime(cal);
                break;
            case ENDING:
                adjustToEndOfTime(cal);
                break;
            default:
                throw new ParseException("invalid qty in data adjustment: " + direction,
                        0);
            }
        else
        {
            throw new ParseException("invalid direction in data adjustment: " + direction,
                    0);
        }
    }

    void adjustToBeginningOfTime(
            final Calendar cal)
    {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    void adjustToEndOfTime(
            final Calendar cal)
    {
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
    }

    void adjustWeekOfMonth(
            final Calendar cal,
            final int qty)
                    throws ParseException
    {
        switch (quantityType)
        {
        case BEGINNING:
            switch (direction)
            {
            case AT:
                cal.set(Calendar.WEEK_OF_MONTH, 1);
                break;
            case NEXTORTHIS:
                if (cal.get(Calendar.WEEK_OF_MONTH) != 1)
                {
                    cal.add(Calendar.MONTH, 1);
                    cal.set(Calendar.WEEK_OF_MONTH, 1);
                }
                break;
            case NEXT:
                cal.add(Calendar.MONTH, 1);
                cal.set(Calendar.WEEK_OF_MONTH, 1);
                break;
            case PREVORTHIS:
                cal.set(Calendar.WEEK_OF_MONTH, 1);
                break;
            case PREV:
                if (cal.get(Calendar.WEEK_OF_MONTH) == 1)
                    cal.add(Calendar.MONTH, -1);
                cal.set(Calendar.WEEK_OF_MONTH, 1);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            break;
        case ENDING: // same as @ew or @eo
            switch (direction)
            {
            case AT:
                cal.add(Calendar.MONTH, 1);
                cal.set(Calendar.WEEK_OF_MONTH, 1);
                cal.add(Calendar.WEEK_OF_MONTH, -1);
                break;
            case NEXTORTHIS:
                cal.set(Calendar.WEEK_OF_MONTH, endWeekOfThisMonth(cal));
                break;
            case NEXT:
                if (cal.get(Calendar.WEEK_OF_MONTH) == endWeekOfThisMonth(cal))
                {
                    cal.add(Calendar.MONTH, 1);
                }
                cal.set(Calendar.WEEK_OF_MONTH, endWeekOfThisMonth(cal));
                break;
            case PREVORTHIS:
                if (cal.get(Calendar.WEEK_OF_MONTH) != endWeekOfThisMonth(cal))
                {
                    cal.add(Calendar.MONTH, -1);
                }
                cal.set(Calendar.WEEK_OF_MONTH, endWeekOfThisMonth(cal));
                break;
            case PREV:
                cal.add(Calendar.MONTH, -1);
                cal.set(Calendar.WEEK_OF_MONTH, endWeekOfThisMonth(cal));
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            break;
        default:
            switch (direction)
            {
            case AT:
                cal.set(Calendar.WEEK_OF_MONTH, qty);
                break;
            case NEXTORTHIS:
                if (cal.get(Calendar.WEEK_OF_MONTH) > qty)
                {
                    cal.add(Calendar.MONTH, 1);
                }
                cal.set(Calendar.WEEK_OF_MONTH, qty);
                break;
            case NEXT:
                if (cal.get(Calendar.WEEK_OF_MONTH) >= qty)
                {
                    cal.add(Calendar.MONTH, 1);
                }
                cal.set(Calendar.WEEK_OF_MONTH, qty);
                break;
            case PREVORTHIS:
                if (cal.get(Calendar.WEEK_OF_MONTH) < qty)
                {
                    cal.add(Calendar.MONTH, -1);
                }
                cal.set(Calendar.WEEK_OF_MONTH, qty);
                break;
            case PREV:
                if (cal.get(Calendar.WEEK_OF_MONTH) <= qty)
                {
                    cal.add(Calendar.MONTH, -1);
                }
                cal.set(Calendar.WEEK_OF_MONTH, qty);
                break;
            case SUBTRACT:
                cal.add(Calendar.WEEK_OF_MONTH, qty);
                break;
            case ADD:
                cal.add(Calendar.WEEK_OF_MONTH, qty);
                break;
            default:
                break;
            }
        }
        adjustToBeginningOfTime(cal);
    }

    void adjustWeekOfYear(
            final Calendar cal,
            final int qty)
                    throws ParseException
    {
        switch (quantityType)
        {
        case BEGINNING:
            switch (direction)
            {
            case AT:
                break;
            case NEXTORTHIS:
                if (cal.get(Calendar.WEEK_OF_YEAR) != 1)
                {
                    cal.add(Calendar.YEAR, 1);
                }
                break;
            case NEXT:
                cal.add(Calendar.YEAR, 1);
                break;
            case PREVORTHIS:
                break;
            case PREV:
                if (cal.get(Calendar.WEEK_OF_YEAR) == 1)
                    cal.add(Calendar.YEAR, -1);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            cal.set(Calendar.WEEK_OF_YEAR, 1);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            adjustToBeginningOfTime(cal);
            break;

        case ENDING: // same as @ew or @eo
            switch (direction)
            {
            case AT:
                break;
            case NEXTORTHIS:
                break;
            case NEXT:
                if (cal.get(Calendar.WEEK_OF_YEAR) == 53)
                {
                    cal.add(Calendar.YEAR, 1);
                }
                break;
            case PREVORTHIS:
                if (cal.get(Calendar.WEEK_OF_YEAR) != 53)
                {
                    cal.add(Calendar.YEAR, -1);
                }
                break;
            case PREV:
                cal.add(Calendar.YEAR, -1);
                cal.set(Calendar.WEEK_OF_YEAR, 53);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            cal.set(Calendar.WEEK_OF_YEAR, 53);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            adjustToBeginningOfTime(cal);
            break;
        default:
            switch (direction)
            {
            case AT:
                cal.set(Calendar.WEEK_OF_YEAR, qty);
                break;
            case NEXTORTHIS:
                if (cal.get(Calendar.WEEK_OF_YEAR) > qty)
                {
                    cal.add(Calendar.YEAR, 1);
                }
                cal.set(Calendar.WEEK_OF_YEAR, qty);
                break;
            case NEXT:
                if (cal.get(Calendar.WEEK_OF_YEAR) >= qty)
                {
                    cal.add(Calendar.YEAR, 1);
                }
                cal.set(Calendar.WEEK_OF_YEAR, qty);
                break;
            case PREVORTHIS:
                if (cal.get(Calendar.WEEK_OF_YEAR) < qty)
                {
                    cal.add(Calendar.YEAR, -1);
                }
                cal.set(Calendar.WEEK_OF_YEAR, qty);
                break;
            case PREV:
                if (cal.get(Calendar.WEEK_OF_YEAR) <= qty)
                {
                    cal.add(Calendar.YEAR, -1);
                }
                cal.set(Calendar.WEEK_OF_YEAR, qty);
                break;
            case SUBTRACT:
                cal.add(Calendar.WEEK_OF_YEAR, qty);
                break;
            case ADD:
                cal.add(Calendar.WEEK_OF_YEAR, qty);
                break;
            default:
                break;
            }
        }
    }

    void adjustYear(
            final Calendar cal,
            final int qty)
                    throws ParseException
    {
        switch (quantityType)
        {
        case BEGINNING:
            switch (direction)
            {
            case AT:
                break;
            case NEXTORTHIS:
                if (cal.get(Calendar.DAY_OF_YEAR) != 1)
                {
                    cal.add(Calendar.YEAR, 1);
                }
                break;
            case NEXT:
                cal.add(Calendar.YEAR, 1);
                break;
            case PREVORTHIS:
                break;
            case PREV:
                cal.add(Calendar.YEAR, -1);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            cal.set(Calendar.DAY_OF_YEAR, 1);
            adjustToBeginningOfTime(cal);
            break;

        case ENDING: // same as @ew or @eo
            switch (direction)
            {
            case AT:
                break;
            case NEXTORTHIS:
                break;
            case NEXT:
                cal.add(Calendar.YEAR, 1);
                break;
            case PREVORTHIS:
                if (!isEndOfThisYear(cal))
                    cal.add(Calendar.YEAR, -1);
                break;
            case PREV:
                cal.add(Calendar.YEAR, -1);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
            cal.set(Calendar.DATE, 1);
            cal.add(Calendar.YEAR, 1);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.add(Calendar.DATE, -1);
            adjustToEndOfTime(cal);
            break;
        default:
            switch (direction)
            {
            case AT:
                cal.set(Calendar.YEAR, qty);
                break;
            case SUBTRACT:
                cal.add(Calendar.YEAR, qty);
                break;
            case ADD:
                cal.add(Calendar.YEAR, qty);
                break;
            default:
                throw new ParseException("invalid direction in data adjustment: " + direction,
                        0);
            }
        }
    }

    int endWeekOfThisMonth(
            final Calendar cal)
    {
        final Calendar work = Calendar.getInstance();
        work.setTime(cal.getTime());
        work.set(Calendar.DAY_OF_MONTH, 1);
        work.add(Calendar.MONTH, 1);
        work.add(Calendar.DAY_OF_MONTH, -1);
        return work.get(Calendar.WEEK_OF_MONTH);
    }

    boolean isEndOfThisYear(
            final Calendar cal)
    {
        return cal.get(Calendar.DATE) == 31 && cal.get(Calendar.MONTH) == Calendar.DECEMBER;
    }

    /**
     * <p>isInDebug.</p>
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
        int qtyStart;

        switch (tokenValue.charAt(0))
        {
        case '+':
            direction = AdjustmentDirection.ADD;
            qtyStart = 1;
            break;
        case '-':
            direction = AdjustmentDirection.SUBTRACT;
            qtyStart = 1;
            break;
        case '=':
            direction = AdjustmentDirection.AT;
            qtyStart = 1;
            break;
        case '>':
            if (tokenValue.charAt(1) == '=')
            {
                direction = AdjustmentDirection.NEXTORTHIS;
                qtyStart = 2;
            } else
            {
                direction = AdjustmentDirection.NEXT;
                qtyStart = 1;
            }
            break;
        case '<':
            if (tokenValue.charAt(1) == '=')
            {
                direction = AdjustmentDirection.PREVORTHIS;
                qtyStart = 2;
            } else
            {
                direction = AdjustmentDirection.PREV;
                qtyStart = 1;
            }
            break;
        default:
            throw new ParseException("invalid direction: " + tokenValue,
                    0);
        }
        return qtyStart;
    }

    int parseEndOfNumber(
            final String tokenValue,
            int startOfNumber)
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
        case 'o':
            unitOfMeasure = UnitOfMeasure.WEEKOFMONTH;
            break;
        case 'l':
            unitOfMeasure = UnitOfMeasure.MILLISECOND;
            break;
        case 'i':
            unitOfMeasure = UnitOfMeasure.MINUTE;
            break;
        case 'a':
            unitOfMeasure = UnitOfMeasure.DAYOFWEEK;
            break;
        case 'm':
            if (uom.startsWith("min"))
                unitOfMeasure = UnitOfMeasure.MINUTE;
            else if (uom.startsWith("mil") || uom.equals("ms"))
                unitOfMeasure = UnitOfMeasure.MILLISECOND;
            else
                unitOfMeasure = UnitOfMeasure.MONTH;
            break;
        case 'w':
            if (uom.startsWith("weekofm"))
                unitOfMeasure = UnitOfMeasure.WEEKOFMONTH;
            else
                unitOfMeasure = UnitOfMeasure.WEEKOFYEAR;
            break;
        case 'd':
            if (uom.startsWith("dayofw"))
                unitOfMeasure = UnitOfMeasure.DAYOFWEEK;
            else
                unitOfMeasure = UnitOfMeasure.DAY;
            break;
        default:
            throw new ParseException("invalid uom: " + uom,
                    0);
        }
    }
}
