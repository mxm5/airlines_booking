package Repositories.Impls;

import Base.Repository.Repository;
import Domain.Ticket;

public class TicketRepository extends Repository<Ticket,Long> {
    @Override
    public Class<Ticket> getType() {
        return Ticket.class;
    }
}
