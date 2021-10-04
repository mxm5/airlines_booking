package ConsoleView;

import Base.View.View;
import Domain.Customer;
import Domain.Moderator;
import Domain.enums.Roles;
import Util.Context;


public class Login extends View {


    Roles userRole;

    public Login(Roles userRole) {
        {

            String username = enterValue("enter your user name", 3);

            String password = enterValue("enter your password", 3);

            if (userRole != Roles.customer) {
                Moderator loginModerator = Context.getModeratorService().loginModerator(username, password);
                if (loginModerator != null) {

                    Context.setCurrentModerator(loginModerator);
                    new ModeratorDashboard();
                } else {
                    warning("wrong information try again");
                    new Welcome();
                }
            } else {
                Customer loginCustomer = Context.getCustomerService().loginCustomer(username, password);
                if (loginCustomer != null) {

                    Context.setCurrentCustomer(loginCustomer);
                    new CustomerDashboard();
                } else {
                    warning("wrong information try again");
                    new Welcome();
                }

            }
        }

    }
}

