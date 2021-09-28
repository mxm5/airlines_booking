package Services.Impls;

import Domain.Ticket;
import Repositories.Impls.ModeratorRepository;
import Repositories.Impls.TicketRepository;
import Util.FakerUtil;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static Util.FakerUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ModeratorServiceTest {
    @Test
    void createATripWithTickets() {
        ModeratorRepository repository = new ModeratorRepository();
        ModeratorService moderatorService = new ModeratorService(
                repository
        );

        Ticket tripTicket = fakeTicket();

        moderatorService.createATripWithTickets(tripTicket, 20);
        TicketRepository ticketRepository = new TicketRepository();
        Collection<Ticket> all = ticketRepository.getAll();
        all.forEach(ticket -> {
                    assertEquals(
                            ticket.getProviderCompany(),
                            tripTicket.getProviderCompany()
                    );
                    System.out.println(ticket);
                }
        );
        assertEquals(20, all.size());

    }
}