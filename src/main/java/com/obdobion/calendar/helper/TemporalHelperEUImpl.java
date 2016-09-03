package com.obdobion.calendar.helper;

public class TemporalHelperEUImpl extends TemporalHelperAbstractImpl
{
    @Override
    void createMonthFormat1(final int space, final char sep)
    {
        String dFormat;
        String dMatcher;
        dMatcher = ddFmt + ' ' + mmNameFmt + ", " + yyFmt;
        dFormat = patternDay + ' ' + patternMonthName + ", " + patternYear;
        createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);
    }

    @Override
    void createMonthFormat2(final int space, final char sep)
    {
        String dFormat;
        String dMatcher;
        dMatcher = ddFmt + sep + mmFmt + sep + yyFmt;
        dFormat = patternDay + sep + patternMonth + sep + patternYear;
        createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);
    }
}
