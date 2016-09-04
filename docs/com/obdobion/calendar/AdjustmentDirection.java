package com.obdobion.calendar;

import java.text.ParseException;

/**
 *
 * @author Chris DeGreef fedupforone@gmail.com
 *
 */
enum AdjustmentDirection
{
    ADD('+'),
    AT('='),
    NEXT('>'),
    NEXTORTHIS('>', '='),
    PREV('<'),
    PREVORTHIS('<', '='),
    SUBTRACT('-');

    /**
     * <p>
     * find.
     * </p>
     *
     * @param tokenValue a {@link java.lang.String} object.
     * @return a {@link com.obdobion.calendar.AdjustmentDirection} object.
     * @throws java.text.ParseException if any.
     */
    static public AdjustmentDirection find(final String tokenValue) throws ParseException
    {
        char char1 = 0x00;
        char char2 = 0x00;

        if (tokenValue.length() == 0)
            throw new ParseException("empty token when looking for direction", 0);
        if (tokenValue.length() >= 1)
            char1 = tokenValue.charAt(0);
        if (tokenValue.length() >= 2)
            char2 = tokenValue.charAt(1);

        AdjustmentDirection bestSoFar = null;
        final AdjustmentDirection[] all = values();
        for (int d = 0; d < all.length; d++)
            if (all[d].firstChar == char1)
                if (all[d].secondChar == 0x00)
                {
                    if (bestSoFar == null)
                        bestSoFar = all[d];
                } else if (all[d].secondChar == char2)
                    return all[d];
        if (bestSoFar != null)
            return bestSoFar;
        throw new ParseException("invalid direction: " + tokenValue, 0);
    }

    char firstChar;
    char secondChar;

    AdjustmentDirection(final char char1)
    {
        firstChar = char1;
        secondChar = (char) 0x00;
    }

    AdjustmentDirection(final char char1, final char char2)
    {
        firstChar = char1;
        secondChar = char2;
    }

    /**
     * <p>
     * size.
     * </p>
     *
     * @return a int.
     */
    public int size()
    {
        if (secondChar == 0x00)
            return 1;
        return 2;
    }
}
