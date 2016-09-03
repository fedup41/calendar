package com.obdobion.calendar.helper;

public class TemporalHelperUSImpl extends TemporalHelperAbstractImpl
{
    @Override
    void createMonthFormat1(final int space, final char sep)
    {
        String dFormat;
        String dMatcher;
        dMatcher = mmNameFmt + ' ' + ddFmt + ", " + yyFmt;
        dFormat = patternMonthName + ' ' + patternDay + ", " + patternYear;
        createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);
    }

    @Override
    void createMonthFormat2(final int space, final char sep)
    {
        String dFormat;
        String dMatcher;
        dMatcher = mmFmt + sep + ddFmt + sep + yyFmt;
        dFormat = patternMonth + sep + patternDay + sep + patternYear;
        createWithTimePattern(dFormat, dMatcher, spaceSeps[space]);
    }
}
