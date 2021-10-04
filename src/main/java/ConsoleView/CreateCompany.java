package ConsoleView;

import Base.View.View;
import Domain.Company;
import Util.Context;

public class CreateCompany extends View {
    public CreateCompany() {
        String brandName = enterValue("enter company brand name");
        Company company = new Company(brandName);

        boolean registerCompany = Context.getModeratorService().registerCompany(
                company
        );
        if (registerCompany) {
            success("company registered");
        }
        new ModeratorDashboard();
    }
}
