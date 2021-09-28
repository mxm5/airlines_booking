package Services.Impls;


import Base.Service.Service;
import Domain.Customer;
import Domain.enums.OrderBy;
import Repositories.Impls.CustomerRepository;
import Services.Apis.CustomerServiceApi;

public class CustomerService extends Service<Customer, Long, CustomerRepository> implements CustomerServiceApi {

    public CustomerService(CustomerRepository repository) {
        super(repository);
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
    public void searchTripsByOriginAndDestinationOrderByPrice(String from, String to, OrderBy sorting) {
        repository.searchTickets(from,to,sorting);
    }

    @Override
    public void changePassword() {

    }
}
