package Services.Impls;


import Base.Service.Service;
import Domain.Ticket;
import Repositories.Impls.TicketRepository;

public class TicketService extends Service<Ticket, Long, TicketRepository> {

    public TicketService(TicketRepository repository) {
        super(repository);
    }
}
