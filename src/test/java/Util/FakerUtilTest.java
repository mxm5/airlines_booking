package Util;

import com.github.javafaker.DateAndTime;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static Util.FakerUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class FakerUtilTest {
    @Test
    void fakerDateTime() {

        Time time = Time.valueOf("12:46:00");
        System.out.println(time);
        Date pastDate = faker.date().birthday();
        LocalDateTime.ofInstant(pastDate.toInstant(), ZoneId.systemDefault());

    }
}