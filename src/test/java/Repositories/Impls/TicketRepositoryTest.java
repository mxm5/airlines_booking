package Repositories.Impls;

import Domain.Company;
import Domain.Customer;
import Domain.Moderator;
import Domain.Ticket;
import Domain.enums.OrderBy;
import Repositories.Apis.TicketRepositoryApi;
import Services.Impls.ModeratorService;
import Util.Context;
import Util.DataBaseUtil;
import Util.FakerUtil;
import com.google.common.collect.Streams;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.*;
import javax.xml.crypto.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Util.DataBaseUtil.entityManager;
import static Util.FakerUtil.*;
import static Util.FakerUtil.fakeTicket;
import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    private static ModeratorService moderatorService;

    private static TicketRepository ticketRepository;

    @BeforeAll
    static void beforeAll() {
        moderatorService = new ModeratorService(new ModeratorRepository());
        ticketRepository = new TicketRepository();

    }

    @Test
    
    void searchUsingQuery() {


        moderatorService.createATripWithTickets(fakeTicket(), 5);
        moderatorService.createATripWithTickets(fakeTicket(), 5);
        moderatorService.createATripWithTickets(fakeTicket(), 5);
        Ticket ticket1 = fakeTicket();
        moderatorService.createATripWithTickets(ticket1, 5);
        moderatorService.createATripWithTickets(fakeTicket(), 5);
        TicketRepository ticketRepository = new TicketRepository();
        CompanyRepository companyRepository = new CompanyRepository();
        ticketRepository.getAll().forEach(System.out::println);
        String queryCityHome = "";
        String queryCityDestination = "";
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Order homeAsc = builder.asc(query.from(Ticket.class).get("home"));
        Order homeDesc = builder.desc(query.from(Ticket.class).get("home"));
        Order destinationAsc = builder.asc(query.from(Ticket.class).get("destination"));
        Order destinationDesc = builder.desc(query.from(Ticket.class).get("destination"));
        Order priceAsc = builder.asc(query.from(Ticket.class).get("price"));
        Order priceDesc = builder.desc(query.from(Ticket.class).get("price"));
        Order companyDesc = builder.desc(query.from(Ticket.class).get("providerCompany"));
        Order companyAsc = builder.asc(query.from(Ticket.class).get("providerCompany"));

        Predicate homeLike = builder.like(query.from(Ticket.class).get("home"), "%" + queryCityHome + "%");
        Predicate homeEqualToQuery = builder.equal(query.from(Ticket.class).get("home"), queryCityHome);
        Predicate likeOrEqual = builder.or(homeEqualToQuery, homeLike);
        Predicate destinationEqualToQuery = builder.equal(query.from(Ticket.class).get("destination"), queryCityDestination);
        Predicate homeAndDestinationEqualsToQuery = builder.and(
                homeEqualToQuery,
                destinationEqualToQuery
        );
/*        movingDateAsc
                movingDateDesc
                companyEqualsToQuery
                        companyLikeQuery*/
        query.select(query.from(Ticket.class))
                .where(homeAndDestinationEqualsToQuery)
                .orderBy(homeAsc);
        CriteriaQuery<Ticket> ticketCriteriaQuery = query.select(
                        query.from(Ticket.class))
                .where(homeAndDestinationEqualsToQuery)
                .orderBy(companyDesc);
        System.out.println("=================================");
        List<Ticket> resultList =
                entityManager.createQuery(ticketCriteriaQuery).getResultList();
        resultList.forEach(ticket ->
                {
                    System.out.println(ticket.getProviderCompany());
                    System.out.println(ticket.getId());
                }
        );

        Stream<Company> sorted = companyRepository.getAll().stream().sorted(
                Comparator.comparing(Company::getBrandName).reversed());
        sorted.forEach(company -> System.out.println(company.getBrandName()));

//        sorted.forEach(company -> System.out.println(company.getBrandName()));

    }

    @Test
    void sortTicketsByHomeCity() {

    }

    @Test
    void sortTicketsByDestinationCity() {

    }

    @Test
    void sortHomeAndDestinyTicketsByPrice() {
        Ticket ticket = fakeTicket();
        Ticket ticketB = fakeTicket();
        ticketB.setHome(ticket.getHome());
        ticketB.setDestination(ticket.getDestination());
        moderatorService.createATripWithTickets(ticket, 5);

        moderatorService.createATripWithTickets(ticketB, 5);
        List<Ticket> ticketsList = ticketRepository.sortHomeAndDestinyTicketsByPrice(
                ticket.getHome(),
                ticket.getDestination(),
                OrderBy.Asc
        );
        System.out.println("=============================");
        ticketsList.forEach(ticket1 -> {
            System.out.println(ticket1.getHome()+" "+ticket1.getPrice());
        });
        boolean b = ticketsList.get(0).getPrice() <= ticketsList.get(8).getPrice();
        assertTrue(b);

    }

    @Test
    void sortHomeAndDestinyTicketsByCompanyBrand() {
        Ticket ticket = fakeTicket();
        Ticket ticketB = fakeTicket();
        Ticket ticketC = fakeTicket();
        ticketB.setHome(ticket.getHome());
        ticketB.setDestination(ticket.getDestination());
        ticketC.setHome(ticket.getHome());
        ticketC.setDestination(ticket.getDestination());
        moderatorService.createATripWithTickets(ticket, 5);
        moderatorService.createATripWithTickets(ticketB, 5);
        moderatorService.createATripWithTickets(ticketC, 5);
        List<Ticket> ticketsList = ticketRepository.sortHomeAndDestinyTicketsByCompanyBrand(
                ticket.getHome(),
                ticket.getDestination(),
                OrderBy.Asc
        );
        System.out.println("=============================");
        ticketsList.forEach(ticket1 -> {
            System.out.println(ticket1.getProviderCompany().getBrandName()+" "+ticket1.getPrice());
        });
    }

    @Test
    void getTicketsCountProvidedByTrip() throws Exception {
        Company company = new Company("xyz");
        Ticket ticket = fakeTicket();
        ticket.setProviderCompany(company);
        moderatorService.createATripWithTickets(ticket,5);
        ticket =fakeTicket();
        ticket.setProviderCompany(company);
        moderatorService.createATripWithTickets(ticket,5);ticket =fakeTicket();
        ticket.setProviderCompany(company);
        moderatorService.createATripWithTickets(ticket,5);ticket =fakeTicket();
        ticket.setProviderCompany(company);
        moderatorService.createATripWithTickets(ticket,5);
        ticket = fakeTicket();
        ticket.setProviderCompany(company);
        moderatorService.createATripWithTickets(ticket, 5);
        Moderator moderator = fakeModerator();
        moderator.setCompany(company);
        company.getModerators().add(moderator);
        ModeratorRepository moderatorRepository = new ModeratorRepository();
        moderatorRepository.save(moderator);
        Context.setCurrentModerator(moderator);
        List<Object> ticketsCountProvidedByTrip = ticketRepository.getTicketsCountProvidedByTrip();
//        for(Object object : ticketsCountProvidedByTrip) {
//            Map row = (Map)object;
//            System.out.println(row);
//        }
    }
}