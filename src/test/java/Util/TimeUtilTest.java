package Util;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilTest {
    @Test
    void timesMustNotBeSame() {
        Timestamp t1 = TimeUtil.nowToTimestamp();
        Timestamp t2 = TimeUtil.nowToTimestamp();
        System.out.println(t1);
        System.out.println(t2);
        assertNotEquals(t1 ,t2);

    }
}