package Services.Apis;

import Base.Service.ServiceApi;
import Domain.Customer;
import Domain.Ticket;
import Domain.enums.OrderBy;

import java.util.List;

public interface CustomerServiceApi extends ServiceApi<Customer, Long> {
    Customer loginCustomer(String username,String password);//todo

    boolean registerCustomer(Customer customer);//todo

    void buyTicket(Ticket ticket);//todo

    void refundTicket();//todo


    List<Ticket> searchTicketsOrderByPrice(String from, String to, OrderBy sorting);

    List<Ticket> searchTicketsOrderByCompany(String from, String to, OrderBy sorting);

    List<Ticket> searchTicketsOrderByMovingDate(String from, String to, OrderBy sorting);

    List<Ticket> searchTicketsOrderByArrivingDate(String from, String to, OrderBy sorting);

    List<Ticket> sortTicketsByHomeCity(OrderBy orderBy);

    List<Ticket> sortTicketsByDestinationCity(OrderBy orderBy);

    void changePassword();//todo
}
