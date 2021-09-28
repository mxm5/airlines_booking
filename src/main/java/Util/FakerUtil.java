package Util;

import Domain.Company;
import Domain.Customer;
import Domain.Moderator;
import Domain.Ticket;
import Domain.enums.Roles;
import com.github.javafaker.Faker;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FakerUtil {
    public static final Faker faker = new Faker();

    public static Customer fakeCustomer() {
        return new Customer(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                "1234"
        );
    }

    //    public static Ticket fakeTicket() {
//        return
//    }
    //todo add all entities fake gen
    // todo add
    public static LocalDateTime fakeLocalDateTimePast() {

        Date pastDate = faker.date().birthday();
        LocalDate.ofInstant(pastDate.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.ofInstant(pastDate.toInstant(), ZoneId.systemDefault());

    }

    public static LocalDateTime fakeLocalDateTimeFuture() {

        Date futureDate = faker.date().future(15, TimeUnit.DAYS);
        return LocalDateTime.ofInstant(futureDate.toInstant(), ZoneId.systemDefault());
    }

    public static Ticket fakeTicket() {
        return new Ticket(
                fakeLocalDateTimePast(),
                fakeLocalDateTimeFuture(),
                new Random().nextInt(1000, 10000),
                new Company(faker.company().name()),
                faker.address().cityName(),
                "the "+faker.address().cityName()
        );
    }

    public static Moderator fakeModerator() {
        return new Moderator(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                "1234",
                Roles.normalAuthority
        );
    }
}
