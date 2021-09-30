package Repositories.Impls;

import Base.Repository.Repository;
import Domain.Customer;
import Domain.Moderator;
import Domain.Ticket;
import Domain.enums.OrderBy;
import Repositories.Apis.CustomerRepositoryApi;

import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerRepository extends Repository<Customer, Long> implements CustomerRepositoryApi {

    private final TicketRepository ticketRepository = new TicketRepository();

    @Override
    public Class<Customer> getType() {
        return Customer.class;
    }

    @Override
    public Customer existsCustomerWithUsernameAndPassWord(String username, String password) {
        TypedQuery<Customer> query = getEntityManager().createQuery(
                " FROM " + getType().getSimpleName() + " u WHERE u.userName = '" + username + "' " +
                        "AND u.password = '" + password + "' "
                , getType());
        try {

            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("not found");
            return null;
        }
    }


}
