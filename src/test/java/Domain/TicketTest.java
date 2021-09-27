package Domain;

import Util.DataBaseUtil;
import Util.FakerUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static Util.DataBaseUtil.*;
import static Util.FakerUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void ticketDualRelation() {
        Ticket ticket = fakeTicket();
        Customer customer = fakeCustomer();
        ticket.setOwner(customer);
        simpleSave(ticket);
        // BEST NIGGER
        assertEquals(0,customer.getTickets().size());
        entityManager.detach(customer); // very important thing

        assertEquals(0,customer.getTickets().size());
        Long ticketId = ticket.getId();
        Long customerId = customer.getId();


        Customer customerById = entityManager.find(
                Customer.class,
                customerId);
        assertTrue(customerById.getTickets().contains(ticket));
        assertEquals(1,customerById.getTickets().size());

        entityManager.remove(customerById);// when you detach you must remove

    }
}