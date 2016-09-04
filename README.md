# com.obdobion.calendar

Seriously, read the [wiki](https://github.com/fedups/com.obdobion.calendar/wiki).

Date Calendar Functions

The calendar project has facilities to create a Java LocalDateTime instance by manipulating a base date.  Everything in this package is accessible via the CalendarFactory class.  

##### Here are a few examples:

_Get a date and time stamp for right now_

    LocalDateTime myDate = CalendarFactory.now();

_Get a date for today with the time part set at 0_

    LocalDateTime myDate = CalendarFactory.today();

_Get the date for the same day as today but 3 weeks from now_

    LocalDateTime myDate = CalendarFactory.today("+21d");

_Get the date / time for exactly 1 hour and 15 minutes from right now_

    LocalDateTime myDate = CalendarFactory.now("+1hour +15min");

_Get a Calendar instance for the very end of last month_

    LocalDateTime myDate = CalendarFactory.today("=bmonth -1month =emonth");

##### The CalendarFactory can also operate on other data types.

_Get the end of the month based on a date from a database_

    LocalDateTime myDate = LocalDateTime.parse("2007-12-03T10:15:30");
    myDate = CalendarFactory.modify(myDatabaseDate, "=emonth");

_Simple conversion of a JSON date without any modifications_

    LocalDateTime myDate = CalendarFactory.modify("2016-05-17T13:01:52.012-0500");
