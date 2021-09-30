package Services.Impls;


import Base.Service.Service;
import Domain.Customer;
import Domain.Ticket;
import Domain.enums.OrderBy;
import Repositories.Impls.CustomerRepository;
import Repositories.Impls.TicketRepository;
import Services.Apis.CustomerServiceApi;

import java.util.List;

public class CustomerService extends Service<Customer, Long, CustomerRepository> implements CustomerServiceApi {

    private TicketRepository ticketRepository;
    public CustomerService(CustomerRepository repository) {
        super(repository);
        ticketRepository = new TicketRepository();
    }

    @Override
    public Customer loginCustomer(String username,String password) {
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
    public void buyTicket() {
        //todo check balance
        //todo check transaction fee
        //todo check availability not sold
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
        return ticketRepository.sortTicketsOrderByMovingDate( from, to, sorting);
    }

    @Override
    public List<Ticket> searchTicketsOrderByArrivingDate(String from, String to, OrderBy sorting) {
        return ticketRepository.sortTicketsOrderByArrivingDate( from, to, sorting);
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
