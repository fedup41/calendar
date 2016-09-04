package com.obdobion.calendar.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * TemporalHelper class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 */
public abstract class TemporalHelperAbstractImpl implements ITemporalHelperImpl
{
    static final String     mmNameFmt           = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)";
    static final String     mmFmt               = "[0-9]{1,2}";
    static final String     ddFmt               = "[0-9]{1,2}";
    static final String     yyFmt               = "[0-9]{4}";

    static final char[]     seps                = { '-', '/' };
    static final char[]     spaceSeps           = { ' ', '_', '@' };
    static final String     hhFmt               = "[0-9]{1,2}";
    static final String     miFmt               = "[0-9]{1,2}";
    static final String     ssFmt               = "[0-9]{1,2}";

    static final String     zzFmt               = "[0-9]{1,3}";
    static final char       tSep                = ':';

    static final String     milliSep            = "\\.";
    static final String     patternYear         = "yyyy";
    static final String     patternMonth        = "M";
    static final String     patternMonthName    = "MMM";

    static final String     patternDay          = "d";
    static final String     patternHour         = "H";
    static final String     patternMinute       = "m";
    static final String     patternSecond       = "s";
    static final String     patternMilli        = "S";
    static final int        numberOfDateFormats = 6;
    static final int        numberOfTimeFormats = 5;
    static final int        numberOfAlgos       = 2;
    static final String     specialAlgoTODAY    = "(?i)today";

    static final String     specialAlgoNOW      = "(?i)now";
    static final int        numberOfOneOffs     = 2;

    int                     fmtMakerIndex       = -1;
    Matcher[]               predefinedMat;
    DateTimeFormatter[]     predefinedFmt;
    boolean[]               predefinedFmtWithDateOnly;
    boolean[]               predefinedFmtWithTimeOnly;
    String[]                predefinedAlg;

    final SimpleDateFormat  outputSDF           = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss.SSS");
    final DateTimeFormatter outputDTF           = DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss.SSS");
    final DateTimeFormatter outputDF            = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final DateTimeFormatter outputTF            = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    void createFormat(final String format)
    {
        createFormat(format, false, false);
    }

    private void createFormat(final String format, final boolean withDateOnly, final boolean withTimeOnly)
    {
        if (format.charAt(0) == '*')
        {
            predefinedFmt[fmtMakerIndex] = null;
            predefinedAlg[fmtMakerIndex] = format.substring(1);
        } else
        {
            predefinedFmt[fmtMakerIndex] = DateTimeFormatter.ofPattern(format.trim());
            predefinedFmtWithDateOnly[fmtMakerIndex] = withDateOnly;
            predefinedFmtWithTimeOnly[fmtMakerIndex] = withTimeOnly;
            predefinedAlg[fmtMakerIndex] = null;
        }
    }

    void createFormatWithDateOnly(final String format)
    {
        createFormat(format, true, false);
    }

    void createFormatWithTimeOnly(final String format)
    {
        createFormat(format, false, true);
    }

    Matcher createMatcher(final String format)
    {
        return Pattern.compile(format.trim()).matcher("");
    }

    abstract void createMonthFormat1(final int space, final char sep);

    abstract void createMonthFormat2(final int space, final char sep);

    /**
     * Create these so that the matchers are paired with the date formats. When
     * the matcher succeeds then the date format will be used.
     */
    synchronized void createPredefinedDateFormats()
    {
        if (predefinedMat != null)
            return;

        final int sizeOfPredefinedArrays = spaceSeps.length
                * numberOfDateFormats
                * seps.length
                * numberOfTimeFormats
                + numberOfTimeFormats
                + numberOfAlgos
                + numberOfOneOffs;
        predefinedMat = new Matcher[sizeOfPredefinedArrays];
        predefinedFmt = new DateTimeFormatter[sizeOfPredefinedArrays];
        predefinedFmtWithDateOnly = new boolean[sizeOfPredefinedArrays];
        predefinedFmtWithTimeOnly = new boolean[sizeOfPredefinedArrays];
        predefinedAlg = new String[sizeOfPredefinedArrays];

        String dFormat;
        String dMatcher;

        for (int space = 0; space < spaceSeps.length; space++)
        {
            for (int s = 0; s < seps.length; s++)
            {
                final char sep = seps[s];
                if (s == 0)
                    createMonthFormat1(space, sep);
                createMonthFormat2(space, sep);

                dMatcher = yyFmt + sep + mmFmt + sep + ddFmt;
                dFormat = patternYear + sep + patternMonth + sep + patternDay;
                createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);

                dMatcher = yyFmt + sep + mmFmt;
                dFormat = patternYear + sep + patternMonth;
                createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);

                dMatcher = mmFmt + sep + yyFmt;
                dFormat = patternMonth + sep + patternYear;
                createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);
                /*
                 * No date, just time of day.
                 */
                createWithTimePattern("", "", spaceSeps[space]);
            }

            dMatcher = specialAlgoTODAY;
            dFormat = "*" + specialAlgoTODAY;
            createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);

            dMatcher = specialAlgoNOW;
            dFormat = "*" + specialAlgoNOW;
            createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);
        }
        /*
         * oneOffs
         */
        predefinedMat[++fmtMakerIndex] = createMatcher(
                yyFmt + "-" + mmFmt + "-" + ddFmt + "T" + hhFmt + ":" + mmFmt + ":" + ssFmt + "." + zzFmt + ".*");
        createFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        predefinedMat[++fmtMakerIndex] = createMatcher(
                yyFmt + "-" + mmFmt + "-" + ddFmt + "T" + hhFmt + ":" + mmFmt + ":" + ssFmt);
        createFormat("yyyy-MM-dd'T'HH:mm:ss");
    }

    void createWithTimePattern(final String dFormat, final String dMatcher, final char space)
    {

        if (dFormat.length() > 0)
        {
            /*
             * Without time.
             */
            predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher);
            createFormatWithDateOnly(dFormat);

            if (predefinedFmt[fmtMakerIndex] == null) // special algo
                return;
        }

        String tFormat;
        String tMatcher;
        /*
         * With HH:mm:ss.SSS
         */
        tMatcher = hhFmt + tSep + miFmt + tSep + ssFmt + milliSep + zzFmt;
        tFormat = patternHour + tSep + patternMinute + tSep + patternSecond + "." + patternMilli;
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        if (dFormat.length() > 0)
            createFormat(dFormat + space + tFormat);
        else
            createFormatWithTimeOnly(tFormat);

        /*
         * With HH:mm:ss
         */
        tMatcher = hhFmt + tSep + miFmt + tSep + ssFmt;
        tFormat = patternHour + tSep + patternMinute + tSep + patternSecond;
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        if (dFormat.length() > 0)
            createFormat(dFormat + space + tFormat);
        else
            createFormatWithTimeOnly(tFormat);
        /*
         * With HH:mm
         */
        tMatcher = hhFmt + tSep + miFmt;
        tFormat = patternHour + tSep + patternMinute;
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        if (dFormat.length() > 0)
            createFormat(dFormat + space + tFormat);
        else
            createFormatWithTimeOnly(tFormat);
        /*
         * With HH
         */
        tMatcher = hhFmt;
        tFormat = patternHour;
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        if (dFormat.length() > 0)
            createFormat(dFormat + space + tFormat);
        else
            createFormatWithTimeOnly(tFormat);
    }

    @Override
    public DateTimeFormatter getOutputDF()
    {
        return outputDF;
    }

    @Override
    public DateTimeFormatter getOutputDTF()
    {
        return outputDTF;
    }

    @Override
    public SimpleDateFormat getOutputSDF()
    {
        return outputSDF;
    }

    @Override
    public DateTimeFormatter getOutputTF()
    {
        return outputTF;
    }

    /**
     * <p>
     * parseSpecialDate.
     * </p>
     *
     * @param pattern a {@link java.lang.String} object.
     * @return a {@link java.util.Date} object.
     */
    LocalDateTime parseSpecialDate(final String pattern)
    {
        if (specialAlgoTODAY.equalsIgnoreCase(pattern))
        {
            final LocalDateTime ldt = LocalDateTime.now();
            return LocalDateTime.of(
                    ldt.getYear(),
                    ldt.getMonthValue(),
                    ldt.getDayOfMonth(),
                    0, 0, 0, 0);
        }
        if (specialAlgoNOW.equalsIgnoreCase(pattern))
            return LocalDateTime.now();
        return LocalDateTime.now();
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
    @Override
    public LocalDateTime parseWithPredefinedParsers(final String valueStr) throws ParseException
    {
        createPredefinedDateFormats();

        for (int f = 0; f < predefinedMat.length; f++)
        {
            if (predefinedMat[f] == null)
                continue;
            predefinedMat[f].reset(valueStr);
            if (predefinedMat[f].matches())
            {
                if (predefinedFmt[f] == null)
                    return parseSpecialDate(predefinedAlg[f]);
                if (predefinedFmtWithDateOnly[f])
                    return LocalDateTime.of(
                            LocalDate.parse(valueStr, predefinedFmt[f]), LocalTime.MIN);
                if (predefinedFmtWithTimeOnly[f])
                    return LocalDateTime.of(
                            LocalDate.MIN, LocalTime.parse(valueStr, predefinedFmt[f]));
                return LocalDateTime.parse(valueStr, predefinedFmt[f]);
            }
        }
        throw new ParseException("not in a predefined date / time format (" + valueStr + ")", 0);
    }
}
