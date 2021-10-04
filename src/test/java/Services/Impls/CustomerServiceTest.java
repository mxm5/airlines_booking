package Services.Impls;

import Domain.Company;
import Domain.Customer;
import Domain.Ticket;
import Repositories.Impls.CompanyRepository;
import Repositories.Impls.CustomerRepository;
import Repositories.Impls.TicketRepository;
import Util.Context;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static Util.Context.*;
import static Util.DataBaseUtil.*;
import static Util.FakerUtil.*;
import static Util.TimeUtil.*;
import static com.google.common.collect.Lists.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private static CustomerRepository customerRepository;
    private static CustomerService customerService;

    @BeforeAll
    static void beforeAll() {
        customerService = new CustomerService(customerRepository);
        customerRepository = new CustomerRepository();

    }

    @Test
    void listSortTest() {
        List<Integer> a = Arrays.asList(1, 2, 3);
//        a.add(4);
        assertThrows(UnsupportedOperationException.class, () -> a.add(4));
        List<Integer> b = new ArrayList();
        b.addAll(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> c = Stream.of(1, 3, 3, 4).toList();
//        c.add(4);
        assertThrows(
                UnsupportedOperationException.class,
                () -> c.add(4));
        List d = newArrayList(1, 3, 3, 2, 4, 5);
        d.add(43);
    }

    @Test
    void buyTicketTest() throws Exception {
        Customer customer = fakeCustomer();
        int customerBalance = 9999999;
        customer.addBalance(customerBalance);
        Ticket ticket = fakeTicket();
        Company company = new Company("hello");
        TicketRepository ticketRepository = new TicketRepository();
        CompanyRepository companyRepository = new CompanyRepository();
        companyRepository.save(company);
        ticket.setProviderCompany(company);
        setCurrentCustomer(customer);
        simpleSave(customer);
        ticketRepository.save(ticket);
//        CustomerRepository customerRepository = new CustomerRepository();
//        CustomerService customerService = new CustomerService(customerRepository);
        customerService.buyTicket(ticket);
        assertEquals(
                company.getBalance(),
                ((long) ticket.getPrice())
        );

    }


    @Test
    void increaseBalance() throws Exception {
        Customer customer = fakeCustomer();
        assertEquals(customer.getBalance(), 0);
        customerRepository.save(customer);
        setCurrentCustomer(customer);
        Integer amount = 10000;
        customerRepository.addBalance(amount);
        assertEquals(customer.getBalance(), amount);
        Ticket ticket = fakeTicket();
        ticket.setPrice(amount);
        simpleSave(ticket);
        customerService.buyTicket(ticket);
        assertEquals(customer.getBalance(), 0);

    }


    @Test
    void buyWith50PercentPriceWhen1HourLeftToMovingDate() {
        Ticket ticket =OneHourToMoveTicket();
        simpleSave(ticket);
        Customer currentCustomer = getCurrentCustomer();

        if (currentCustomer == null) {
            currentCustomer = fakeCustomer();
            simpleSave(currentCustomer);
            setCurrentCustomer(currentCustomer);
        }
        Integer ticketFullPrice = ticket.getPrice();
        currentCustomer.setBalance(ticketFullPrice);
        customerService.buyTicket(ticket);
        assertEquals(currentCustomer.getBalance(),ticketFullPrice/2);
    }







    @Test
    void randomDateBetweenTwoTimes() {

        LocalDateTime now = nowToLocalDateTime();
        LocalDateTime oneHourAgo = now.minusHours(1);
        long minutes = ChronoUnit.MINUTES.between(
                oneHourAgo, LocalDateTime.now()
        ); // = 60
        int randomMinutesInRange0to60 = new Random().nextInt(61);
        LocalDateTime ticketDate = oneHourAgo.plusMinutes(randomMinutesInRange0to60/* 0 to 59 */);
        System.out.println(oneHourAgo.getHour() + ":" + oneHourAgo.getMinute() + " -> 1 hour ago");
        System.out.println(now.getHour() + ":" + now.getMinute() + " -> now");
        System.out.println("+" + randomMinutesInRange0to60 + " minutes later ->");
        System.out.println(ticketDate.getHour() + ":" + ticketDate.getMinute() + " -> ticket time");
        assertTrue(ticketDate.isAfter(oneHourAgo));
        assertTrue(ticketDate.isBefore(now));
    }
}