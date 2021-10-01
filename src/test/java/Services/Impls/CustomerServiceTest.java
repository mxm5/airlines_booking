package Services.Impls;

import Domain.Company;
import Domain.Customer;
import Domain.Ticket;
import Repositories.Impls.CompanyRepository;
import Repositories.Impls.CustomerRepository;
import Repositories.Impls.TicketRepository;
import Util.Context;
import Util.DataBaseUtil;
import Util.FakerUtil;
import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    @Test
    void listSortTest() {
        List<Integer> a = Arrays.asList(1, 2, 3);
//        a.add(4);
        assertThrows(UnsupportedOperationException.class, () -> a.add(4));
        List<Integer> b = new ArrayList();
        b.addAll(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> c = Stream.of(1, 3,3,4).toList();
//        c.add(4);
        assertThrows(
                UnsupportedOperationException.class,
                () -> c.add(4));
        List d = newArrayList(1, 3, 3, 2, 4, 5);
        d.add(43);
    }

    @Test
    void buyTicket() throws Exception {
        Customer customer = FakerUtil.fakeCustomer();
        int customerBalance = 9999999;
        customer.addBalance(customerBalance);
        Ticket ticket = FakerUtil.fakeTicket();
        Company company = new Company("hello");
        TicketRepository ticketRepository = new TicketRepository();
        CompanyRepository companyRepository = new CompanyRepository();
        companyRepository.save(company);
        ticket.setProviderCompany(company);
                Context.setCurrentCustomer(customer);
        DataBaseUtil.simpleSave(customer);
        ticketRepository.save(ticket);
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository);
        customerService.buyTicket(ticket);
        assertEquals(
                company.getBalance(),
                ((long) ticket.getPrice())
        );

    }
}