import Base.View.View;
import Domain.Company;
import Domain.Customer;
import Domain.Ticket;
import Util.Context;
import Util.DataBaseUtil;
import Util.FakerUtil;
import View.Welcome;
import org.jboss.jandex.Main;

import static Util.DataBaseUtil.*;
import static Util.FakerUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainApplication {


    public static void main(String[] args) {
        new Welcome();
    }

}
