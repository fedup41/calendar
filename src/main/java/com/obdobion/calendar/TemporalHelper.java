package com.obdobion.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    static SimpleDateFormat[]            predefinedFmt;

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
        if (format.charAt(0) == '*')
        {
            predefinedFmt[fmtMakerIndex] = null;
            predefinedAlg[fmtMakerIndex] = format.substring(1);
        } else
        {
            predefinedFmt[fmtMakerIndex] = new SimpleDateFormat(format.trim());
            predefinedAlg[fmtMakerIndex] = null;
        }
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
        predefinedFmt = new SimpleDateFormat[spaceSeps.length
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
                dFormat = "MM" + sep + "dd" + sep + "yyyy";
                createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);

                dMatcher = yyFmt + sep + mmFmt + sep + ddFmt;
                dFormat = "yyyy" + sep + "MM" + sep + "dd";
                createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);

                dMatcher = yyFmt + sep + mmFmt;
                dFormat = "yyyy" + sep + "MM";
                createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);

                dMatcher = mmFmt + sep + yyFmt;
                dFormat = "MM" + sep + "yyyy";
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
            createFormat(dFormat);

            if (predefinedFmt[fmtMakerIndex] == null) // special algo
                return;
        }

        String tFormat;
        String tMatcher;
        /*
         * With HH:mm:ss.SSS
         */
        tMatcher = hhFmt + tSep + miFmt + tSep + ssFmt + milliSep + zzFmt;
        tFormat = "HH" + tSep + "mm" + tSep + "ss" + "." + "SSS";
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        createFormat(dFormat + space + tFormat);
        /*
         * With HH:mm:ss
         */
        tMatcher = hhFmt + tSep + miFmt + tSep + ssFmt;
        tFormat = "HH" + tSep + "mm" + tSep + "ss";
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        createFormat(dFormat + space + tFormat);
        /*
         * With HH:mm
         */
        tMatcher = hhFmt + tSep + miFmt;
        tFormat = "HH" + tSep + "mm";
        predefinedMat[++fmtMakerIndex] = createMatcher(dMatcher + space + tMatcher);
        createFormat(dFormat + space + tFormat);
        /*
         * With HH
         */
        tMatcher = hhFmt;
        tFormat = "HH";
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
    static Date parseSpecialDate(final String pattern)
    {
        if (TemporalHelper.specialAlgoTODAY.equalsIgnoreCase(pattern))
        {
            final Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        }
        if (TemporalHelper.specialAlgoNOW.equalsIgnoreCase(pattern))
            return new Date();
        return new Date();
    }

    static public Date parseWithPredefinedParsers(final String valueStr) throws Exception
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
                return TemporalHelper.predefinedFmt[f].parse(valueStr);
            }
        }
        throw new Exception("not in a predefined date / time format (" + valueStr + ")");
    }
}
