package View;

import Base.View.View;
import Domain.enums.Roles;
import Util.Context;
import com.google.common.collect.Lists;

public class ModeratorDashboard extends View {
    public ModeratorDashboard() {
        boolean hasNoCompany = Context.getCurrentModerator().getCompany() == null;
        int i;
        if (hasNoCompany)
            i = printOptions(
                    "create a company",
                    "exit");
        else {
            i = printOptions("create trip tickets",
                    "view company tickets ",
                    "exit");
        }
        int opt = selectOpt(i);

        boolean viewing = true;
        while (viewing)
            switch (opt) {
                case 1 -> {
                    if (hasNoCompany)
                        new CreateCompany();
                    else
                        new CreateTicket();
                }

                case 2 -> {
                    if (hasNoCompany) {
                        viewing = false;
                        exit();
                    } else
                        new ViewCompanyTickets();
                }
                case 3 -> {
                    viewing = false;
                    exit();
                }
            }

    }
}
