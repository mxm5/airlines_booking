package Repositories.Apis;

import Base.Repository.RepositoryApi;
import Base.Service.ServiceApi;
import Domain.Customer;
import Domain.Ticket;
import Domain.enums.OrderBy;

import java.util.List;

public interface CustomerRepositoryApi extends RepositoryApi<Customer,Long> {

    Customer existsCustomerWithUsernameAndPassWord(String username,String password );



}
