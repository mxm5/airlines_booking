package Domain;

import Util.DataBaseUtil;
import Util.FakerUtil;
import org.junit.jupiter.api.Test;

import static Util.DataBaseUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void createACustomer() {
        Customer customer = new Customer(
                "moh",
                "mohammadi",
                "mxmxm",
                "123");
        assertNull(customer.getId());
        simpleSave(customer);
        System.out.println(customer);
        Long id = customer.getId();
        assertNotNull(id);
        Class<Customer> customerClass = Customer.class;
        Customer customerQ = entityManager.find(customerClass, id);
        customerQ.setBalance(1000L);
        entityManager.merge(customerQ);
        Long balance = entityManager.find(customerClass, id).getBalance();
        assertEquals(1000L,balance);
    }

    @Test
    void customerCanHaveTicket() {
        Customer customer = FakerUtil.fakeCustomer();
//        customer.getTickets().add(

//        );
        simpleSave(customer);
    }
}