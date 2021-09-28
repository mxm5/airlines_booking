package Services.Apis;

import Base.Service.ServiceApi;
import Domain.Customer;
import Domain.enums.OrderBy;

public interface CustomerServiceApi extends ServiceApi<Customer, Long> {
    Customer loginCustomer(String username,String password);//todo

    boolean registerCustomer(Customer customer);//todo

    void buyTicket();//todo

    void refundTicket();//todo

    void searchTripsByOriginAndDestinationOrderByPrice(
            String from,
            String to,
            OrderBy sorting
    );//todo

    void changePassword();//todo
}
