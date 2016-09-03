How do I use this?  These are some examples of how to modify a date.

_Get a date and time stamp for right now_

    LocalDateTime myDate = CalendarFactory.now();

_Get a date for today with the time part set at 0_

    LocalDateTime myDate = CalendarFactory.today();

_Get the date for the same day as today but 3 weeks from now_

    LocalDateTime myDate = CalendarFactory.today("+3w");

_Get the date / time for exactly 1 hour and 15 minutes from right now_

    LocalDateTime myDate = CalendarFactory.now("+1hour +15min");

_Get a Calendar instance for the very end of last month_

    LocalDateTime myDate = CalendarFactory.today("=bmonth -1month =emonth");

##### The CalendarFactory can also operate on other data types.

_Get the end of the month based on a date from a database_

    LocalDateTime myDate = CalendarFactory.modify(myDatabaseDate, "=emonth");

_Simple conversion of a JSON date without any modifications_

    LocalDateTime myDate = CalendarFactory.modify("2016-05-17T13:01:52.012-0500");