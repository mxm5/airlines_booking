package ConsoleView;

import Base.View.View;
import Util.Context;

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
                    } else {
                        viewing = false;
                        new ViewCompanyTickets();
                    }
                }
                case 3 -> {
                    exit();
                    viewing = false;
                }
            }

    }
}
