package Repositories.Impls;

import Base.Repository.Repository;
import Domain.Company;
import Domain.Ticket;
import Domain.Ticket;
import Domain.enums.OrderBy;
import Repositories.Apis.TicketRepositoryApi;
import Util.Context;

import javax.persistence.Query;
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
                        + " and t.owner = null"
                        + " order by t.price " + (orderBy == OrderBy.Asc ? "asc" : "desc")
                , Ticket.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<Ticket> sortHomeAndDestinyTicketsByCompanyBrand(String home, String destination, OrderBy orderBy) {

        TypedQuery<Ticket> query = getEntityManager().createQuery(
                " from " + getType().getSimpleName() + " t "
                        + " where t.home = '" + home.toString() + "' "
                        + " and t.destination = '" + destination.toString() + "'"
                        + " and t.owner = null"
                        + " order by t.providerCompany.brandName " + (orderBy == OrderBy.Asc ? "asc" : "desc")
                , Ticket.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Ticket> sortTicketsOrderByMovingDate(String from, String to, OrderBy sorting) {

        TypedQuery<Ticket> query = getEntityManager().createQuery(
                " from " + getType().getSimpleName() + " t "
                        + " where t.home = '" + from.toString() + "' "
                        + " and t.destination = '" + to.toString() + "'"
                        + " and t.owner = null"
                        + " order by t.movingDate " + (sorting == OrderBy.Asc ? "asc" : "desc")
                , Ticket.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Ticket> sortTicketsOrderByArrivingDate(String from, String to, OrderBy sorting) {

        TypedQuery<Ticket> query = getEntityManager().createQuery(
                " from " + getType().getSimpleName() + " t "
                        + " where t.providerCompany = " + from.toString() + " "
                        + " and t.destination = '" + to.toString() + "'"
                        + " and t.owner = null"
                        + " order by t.arrivingDate " + (sorting == OrderBy.Asc ? "asc" : "desc")
                , Ticket.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List getTicketsCountProvidedByTrip() {
        Company company = Context.getCurrentModerator().getCompany();
        if (company == null) {
            return null;
        } else {
            //todo////////////////////////////
            String destination = null;
            Query query = getEntityManager().createNativeQuery(
                    "select count(t) ,t." + destination + " , t.home from " + getType().getSimpleName() + " t "
                            + " where t.providerCompany = " + company + " "
                            + " group by t.destination , t.home"


            );

            try {
                return query.getResultList();
            } catch (Exception e) {
                return null;
            }
        }

    }
}
