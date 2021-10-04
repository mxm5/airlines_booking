package ConsoleView;

import Base.View.View;
import Domain.enums.Roles;

public class Welcome extends View {

    public Welcome() {
        int i = printOptions(
                "login as moderator",
                "register as Moderator",
                "login as Customer",
                "register as Customer",
                "exit");
        int opt = selectOpt(i);


        switch (opt) {
            case 1 -> new Login(Roles.disabled);
            case 2 -> new Register(Roles.disabled);
            case 3 -> new Login(Roles.customer);
            case 4 -> new Register(Roles.customer);
            case 5 -> {
                printTitle("good bye");
                print("exiting ...");
            }
        }

    }
}
