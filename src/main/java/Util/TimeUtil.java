package Util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;

public class TimeUtil {

    //    public static void now() {
//        return Timestamp.from(Instant.now());
//    }
    public static Timestamp nowToTimestamp() {
        return Timestamp.from(Instant.now());
    }

    public static LocalDateTime nowToLocalDateTime() {
        Instant now = Instant.now();
        return Timestamp.from(now).toLocalDateTime();
    }

    public static Date nowToSqlDate() {
        Timestamp fromInstant = Timestamp.from(Instant.now());
        return new Date(fromInstant.getTime());
    }

    public static Date fourYearsFromNow() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(nowToTimestamp());
        cal.add(Calendar.YEAR, 4);
        return new Date(cal.getTime().getTime());

//        todo make time format
//        todo make to sql.date and local datetime
//        todo for ticket we must check that arr time is after move time
        /*
         *
         *   ts.setTime(cal.getTime().getTime()); // or
         *   ts = new Timestamp(cal.getTime().getTime());
         *   return new Timestamp(cal.getTime().getTime());
         *
         */
    }
}
