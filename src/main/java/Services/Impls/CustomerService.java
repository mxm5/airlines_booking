package Services.Impls;


import Base.Service.Service;
import Domain.Company;
import Domain.Customer;
import Domain.Ticket;
import Domain.enums.OrderBy;
import Exceptions.NotEnoughBalance;
import Exceptions.TicketSold;
import Repositories.Impls.CustomerRepository;
import Repositories.Impls.TicketRepository;
import Services.Apis.CustomerServiceApi;
import Util.Context;
import Util.DataBaseUtil;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

import static Util.DataBaseUtil.*;
import static Util.TimeUtil.nowToLocalDateTime;

public class CustomerService extends Service<Customer, Long, CustomerRepository> implements CustomerServiceApi {

    private TicketRepository ticketRepository;

    public CustomerService(CustomerRepository repository) {
        super(repository);
        ticketRepository = new TicketRepository();
    }

    @Override
    public Customer loginCustomer(String username, String password) {
        return repository.existsCustomerWithUsernameAndPassWord(
                username,
                password);
    }

    @Override
    public boolean registerCustomer(Customer customer) {
        try {
            repository.save(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void buyTicket(Ticket ticket) {
        LocalDateTime now = nowToLocalDateTime();
        LocalDateTime oneHourAgo = now.minusHours(1);
        LocalDateTime movingDate = ticket.getMovingDate();
        if (movingDate.isAfter(oneHourAgo)&&movingDate.isBefore(now)) {
            Integer halfOfPrice = ticket.getPrice() / 2;
            ticket.setPrice(halfOfPrice);
        }
        Customer currentCustomer = Context.getCurrentCustomer();
        // check balance
        int customerBalance = currentCustomer.getBalance();
        // check transaction fee
        Company providerCompany = ticket.getProviderCompany();
        Integer price = ticket.getPrice();
        // check availability not sold
        if (customerBalance >= price) {
            if (ticket.getOwner() == null) {

                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    currentCustomer.subBalance(price);
                    providerCompany.addBalance(price);
                    currentCustomer.getTickets().add(ticket);
                    ticket.setOwner(currentCustomer);
                    simpleUpdateInTx(ticket);
                    simpleUpdateInTx(currentCustomer);
                    simpleUpdateInTx(providerCompany);
                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    transaction.rollback();
                }
            } else throw new TicketSold("this ticket sold");
        } else throw new NotEnoughBalance("not enough balance");
        //todo subtract price from balance
    }




    @Override
    public void refundTicket() {

    }


    @Override
    public List<Ticket> searchTicketsOrderByPrice(String from, String to, OrderBy sorting) {
        return ticketRepository.sortHomeAndDestinyTicketsByPrice(
                from,
                to,
                sorting
        );
    }

    @Override
    public List<Ticket> searchTicketsOrderByCompany(String from, String to, OrderBy sorting) {
        return ticketRepository.sortHomeAndDestinyTicketsByCompanyBrand(
                from,
                to,
                sorting
        );
    }

    @Override
    public List<Ticket> searchTicketsOrderByMovingDate(String from, String to, OrderBy sorting) {
        return ticketRepository.sortTicketsOrderByMovingDate(from, to, sorting);
    }

    @Override
    public List<Ticket> searchTicketsOrderByArrivingDate(String from, String to, OrderBy sorting) {
        return ticketRepository.sortTicketsOrderByArrivingDate(from, to, sorting);
    }

    @Override
    public List<Ticket> sortTicketsByHomeCity(OrderBy orderBy) {
        return ticketRepository.sortTicketsByHomeCity(orderBy);
    }

    @Override
    public List<Ticket> sortTicketsByDestinationCity(OrderBy orderBy) {
        return ticketRepository.sortTicketsByDestinationCity(orderBy);
    }




    @Override
    public void changePassword() {

    }
}
