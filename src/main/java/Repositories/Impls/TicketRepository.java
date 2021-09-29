package Repositories.Impls;

import Base.Repository.Repository;
import Domain.Ticket;
import Domain.Ticket;
import Domain.enums.OrderBy;
import Repositories.Apis.TicketRepositoryApi;

import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.List;

public class TicketRepository extends Repository<Ticket, Long> implements TicketRepositoryApi {
    @Override
    public Class<Ticket> getType() {
        return Ticket.class;
    }

    @Override
    public List<Ticket> sortTicketsByHomeCity(OrderBy orderBy) {
        List<Ticket> sortTicketsByTicketHomeCity;
        if (orderBy == OrderBy.Asc)

            sortTicketsByTicketHomeCity = getAll().stream().sorted(
                    Comparator.comparing(
                            Ticket::getHome
                    )).toList();
        else
            sortTicketsByTicketHomeCity = getAll().stream().sorted(
                    Comparator.comparing(
                            Ticket::getHome
                    ).reversed()).toList();
        return sortTicketsByTicketHomeCity;
    }

    @Override
    public List<Ticket> sortTicketsByDestinationCity(OrderBy orderBy) {
        List<Ticket> sortTicketsByTicketDestinationCity;
        if (orderBy == OrderBy.Asc)

            sortTicketsByTicketDestinationCity = getAll().stream().sorted(
                    Comparator.comparing(
                            Ticket::getHome
                    )).toList();
        else
            sortTicketsByTicketDestinationCity = getAll().stream().sorted(
                    Comparator.comparing(
                            Ticket::getHome
                    ).reversed()).toList();
        return sortTicketsByTicketDestinationCity;
    }

    @Override
    public List<Ticket> sortHomeAndDestinyTicketsByPrice(String home, String destination, OrderBy orderBy) {
        TypedQuery<Ticket> query = getEntityManager().createQuery(
                " from " + getType().getSimpleName() + " t "
                        + " where t.home = '" + home.toString() + "' "
                        + " and t.destination = '" + destination.toString() + "'"
                        + " order by t.price " + (orderBy == OrderBy.Asc ? "asc" : "desc")
                , Ticket.class);
        return query.getResultList();

    }

    @Override
    public List<Ticket> sortHomeAndDestinyTicketsByCompanyBrand(String home, String destination, OrderBy orderBy) {

        TypedQuery<Ticket> query = getEntityManager().createQuery(
                " from " + getType().getSimpleName() + " t "
                        + " where t.home = '" + home.toString() + "' "
                        + " and t.destination = '" + destination + "'"
                        + " order by t.providerCompany.brandName " + (orderBy == OrderBy.Asc ? "asc" : "desc")
                , Ticket.class);
        return query.getResultList();
    }

}
