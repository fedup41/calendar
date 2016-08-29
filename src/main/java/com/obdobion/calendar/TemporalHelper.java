package com.obdobion.calendar;

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
public class TemporalHelper
{

    static final String                  mmFmt               = "[0-9]{1,2}";

    static final String                  ddFmt               = "[0-9]{1,2}";

    static final String                  yyFmt               = "[0-9]{4}";
    static final char[]                  seps                = { '-', '/' };
    static final char[]                  spaceSeps           = { ' ', '_', '@' };
    static final String                  hhFmt               = "[0-9]{1,2}";
    static final String                  miFmt               = "[0-9]{1,2}";

    static final String                  ssFmt               = "[0-9]{1,2}";
    static final String                  zzFmt               = "[0-9]{1,3}";
    static final char                    tSep                = ':';
    static final String                  milliSep            = "\\.";
    static Matcher[]                     predefinedMat;
    static DateTimeFormatter[]           predefinedFmt;
    static boolean[]                     predefinedFmtWithDateOnly;

    private static final String          patternYear         = "yyyy";
    private static final String          patternMonth        = "MM";
    private static final String          patternDay          = "dd";
    private static final String          patternHour         = "HH";
    private static final String          patternMinute       = "mm";
    private static final String          patternSecond       = "ss";
    private static final String          patternMilli        = "SSS";

    static String[]                      predefinedAlg;
    static final int                     numberOfDateFormats = 5;
    static final int                     numberOfTimeFormats = 5;
    static int                           fmtMakerIndex       = -1;
    static final int                     numberOfAlgos       = 2;
    static final String                  specialAlgoTODAY    = "today";

    static final String                  specialAlgoNOW      = "now";
    static final int                     numberOfOneOffs     = 1;
    /** Constant <code>outputSDF</code> */
    static public final SimpleDateFormat outputSDF           = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss.SSS");

    static private void createFormat(final String format)
    {
        createFormat(format, false);
    }

    static private void createFormat(final String format, final boolean withDateOnly)
    {
        if (format.charAt(0) == '*')
        {
            predefinedFmt[fmtMakerIndex] = null;
            predefinedAlg[fmtMakerIndex] = format.substring(1);
        } else
        {
            predefinedFmt[fmtMakerIndex] = DateTimeFormatter.ofPattern(format.trim());
            predefinedFmtWithDateOnly[fmtMakerIndex] = withDateOnly;
            predefinedAlg[fmtMakerIndex] = null;
        }
    }

    static private void createFormatWithDateOnly(final String format)
    {
        createFormat(format, true);
    }

    static private Matcher createMatcher(final String format)
    {
        return Pattern.compile(format.trim(), Pattern.CASE_INSENSITIVE).matcher("");
    }

    /**
     * Create these so that the matchers are paired with the date formats. When
     * the matcher succeeds then the date format will be used.
     */
    synchronized static void createPredefinedDateFormats()
    {
        if (predefinedMat != null)
            return;

        predefinedMat = new Matcher[spaceSeps.length
                * numberOfDateFormats
                * seps.length
                * numberOfTimeFormats
                + numberOfAlgos
                + numberOfOneOffs];
        predefinedFmt = new DateTimeFormatter[spaceSeps.length
                * numberOfDateFormats
                * seps.length
                * numberOfTimeFormats
                + numberOfAlgos
                + numberOfOneOffs];
        predefinedFmtWithDateOnly = new boolean[spaceSeps.length
                * numberOfDateFormats
                * seps.length
                * numberOfTimeFormats
                + numberOfAlgos
                + numberOfOneOffs];
        predefinedAlg = new String[spaceSeps.length
                * numberOfDateFormats
                * seps.length
                * numberOfTimeFormats
                + numberOfAlgos
                + numberOfOneOffs];

        String dFormat;
        String dMatcher;

        for (int space = 0; space < spaceSeps.length; space++)
        {
            for (int s = 0; s < seps.length; s++)
            {
                final char sep = seps[s];

                dMatcher = mmFmt + sep + ddFmt + sep + yyFmt;
                dFormat = patternMonth + sep + patternDay + sep + patternYear;
                createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);

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
                 * No date, just time of day, assuming today as the date part.
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
    }

    static private void createWithTimePattern(final String dFormat, final String dMatcher, final char space)
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
        createFormat(dFormat + space + tFormat);
        /*
         * With HH:mm:ss
         */
        tMatcher = hhFmt + tSep + miFmt + tSep + ssFmt;
        tFormat = patternHour + tSep + patternMinute + tSep + patternSecond;
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        createFormat(dFormat + space + tFormat);
        /*
         * With HH:mm
         */
        tMatcher = hhFmt + tSep + miFmt;
        tFormat = patternHour + tSep + patternMinute;
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        createFormat(dFormat + space + tFormat);
        /*
         * With HH
         */
        tMatcher = hhFmt;
        tFormat = patternHour;
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        createFormat(dFormat + space + tFormat);
    }

    /**
     * <p>
     * parseSpecialDate.
     * </p>
     *
     * @param pattern
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Date} object.
     */
    static LocalDateTime parseSpecialDate(final String pattern)
    {
        if (TemporalHelper.specialAlgoTODAY.equalsIgnoreCase(pattern))
        {
            final LocalDateTime ldt = LocalDateTime.now();
            return LocalDateTime.of(
                    ldt.getYear(),
                    ldt.getMonthValue(),
                    ldt.getDayOfMonth(),
                    0, 0, 0, 0);
        }
        if (TemporalHelper.specialAlgoNOW.equalsIgnoreCase(pattern))
            return LocalDateTime.now();
        return LocalDateTime.now();
    }

    /**
     * <p>
     * parseWithPredefinedParsers.
     * </p>
     *
     * @param valueStr
     *            a {@link java.lang.String} object.
     * @return a {@link java.util.Date} object.
     * @throws java.lang.Exception
     *             if any.
     */
    static public LocalDateTime parseWithPredefinedParsers(final String valueStr) throws Exception
    {
        createPredefinedDateFormats();

        for (int f = 0; f < TemporalHelper.predefinedMat.length; f++)
        {
            if (TemporalHelper.predefinedMat[f] == null)
                continue;
            TemporalHelper.predefinedMat[f].reset(valueStr);
            if (TemporalHelper.predefinedMat[f].matches())
            {
                if (TemporalHelper.predefinedFmt[f] == null)
                    return parseSpecialDate(TemporalHelper.predefinedAlg[f]);
                if (TemporalHelper.predefinedFmtWithDateOnly[f])
                    return LocalDateTime.of(
                            LocalDate.parse(valueStr, TemporalHelper.predefinedFmt[f]), LocalTime.MIDNIGHT);
                return LocalDateTime.parse(valueStr, TemporalHelper.predefinedFmt[f]);
            }
        }
        throw new Exception("not in a predefined date / time format (" + valueStr + ")");
    }
}
