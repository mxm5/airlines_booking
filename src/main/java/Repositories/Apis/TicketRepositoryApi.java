package Repositories.Apis;

import Base.Repository.RepositoryApi;
import Domain.Ticket;
import Domain.enums.OrderBy;

import java.util.Collection;
import java.util.List;

public interface TicketRepositoryApi extends RepositoryApi<Ticket,Long> {

    List<Ticket> sortTicketsByHomeCity(OrderBy orderBy);

    List<Ticket> sortTicketsByDestinationCity(OrderBy orderBy);

    List<Ticket> sortHomeAndDestinyTicketsByPrice(String home, String destination, OrderBy orderBy);

    List<Ticket> sortHomeAndDestinyTicketsByCompanyBrand(String home, String destination, OrderBy orderBy);
}
