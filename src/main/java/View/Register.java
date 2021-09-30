package View;

import Base.Entity.Person;
import Base.View.View;
import Domain.Customer;
import Domain.Moderator;
import Domain.enums.Roles;
import Util.Context;

public class Register extends View {

    Roles userRole;

    public Register(
            Roles userRole

    ) {
        this.userRole = userRole;
        String firstName = enterValue("enter your first name", 3);
        String lastName = enterValue("enter your last name", 3);
        String userName = enterValue("enter your personal user name", 3);

        String password = enterPassword();

        if (userRole != Roles.customer) {

            Moderator registeredModerator = new Moderator(firstName, lastName, userName, password, Roles.highAuthority);
            boolean b = Context.getModeratorService().registerModerator(registeredModerator);
            if (b) {
                success("registered moderator ");
            }
        } else {

            Customer registeredCustomer = new Customer(firstName, lastName, userName, password);
            boolean b = Context.getCustomerService().registerCustomer(registeredCustomer);
            if (b) {
                success("registered customer ");
            }
        }
        new Welcome();


    }


}
