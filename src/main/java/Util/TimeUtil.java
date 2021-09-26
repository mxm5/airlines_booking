package Util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;

public class TimeUtil {

    public static Timestamp now() {
        return Timestamp.from(Instant.now());
    }

    public static Date fourYearsFromNow() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.YEAR, 4);
        return new Date(cal.getTime().getTime());

//        todo make time format
   /*
    *
    *   ts.setTime(cal.getTime().getTime()); // or
    *   ts = new Timestamp(cal.getTime().getTime());
    *   return new Timestamp(cal.getTime().getTime());
    *
    */
    }
}
