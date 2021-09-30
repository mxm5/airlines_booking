package View;

import Base.View.View;
import Domain.enums.OrderBy;
import Services.Apis.CustomerServiceApi;
import Util.Context;

public class CustomerDashboard extends View {
    public CustomerDashboard() {
        String startingCity = enterLine("enter the home starting city name");
        String destinationCity = enterLine("enter destination city");

        final CustomerServiceApi cs = Context.getCustomerService();
        boolean noResults = cs.searchTicketsOrderByPrice(startingCity, destinationCity, OrderBy.Asc).size() == 0;
        int i;
        if (noResults) {
            i = printOptions(
                    "return",
                    "exit");
        } else
            i = printOptions(
                    "show all tickets by price ascending",
                    "show all tickets by price descending",

                    "show all tickets by company name ascending ",
                    "show all tickets by company name descending",

                    "show all tickets by Moving Date ascending",
                    "show all tickets by Moving Date descending",

                    "show all tickets by arriving Date ascending",
                    "show all tickets by arriving Date descending",

                    "exit"
            );


        int opt = selectOpt(i);
        switch (opt) {
            case 1 -> {
                if (noResults)
                    new CustomerDashboard();
                else
                    new ShowTicketListByPrice(
                            cs.searchTicketsOrderByPrice(startingCity, destinationCity, OrderBy.Asc)
                    );
            }
            case 2 -> {
                if (noResults)
                    exit();
                else
                    new ShowTicketListByPrice(
                            cs.searchTicketsOrderByPrice(startingCity, destinationCity, OrderBy.Desc)
                    );
            }
            case 3 -> new ShowTicketListByCompany(
                    cs.searchTicketsOrderByCompany(startingCity, destinationCity, OrderBy.Asc)
            );
            case 4 -> new ShowTicketListByCompany(
                    cs.searchTicketsOrderByCompany(startingCity, destinationCity, OrderBy.Desc)
            );
            case 5 -> new ShowTicketListByMovingDate(
                    cs.searchTicketsOrderByMovingDate(startingCity, destinationCity, OrderBy.Asc)
            );
            case 6 -> new ShowTicketListByMovingDate(
                    cs.searchTicketsOrderByMovingDate(startingCity, destinationCity, OrderBy.Desc)
            );
            case 7 -> new ShowTicketListByArrivingDate(
                    cs.searchTicketsOrderByArrivingDate(startingCity, destinationCity, OrderBy.Asc)
            );
            case 8 -> new ShowTicketListByArrivingDate(
                    cs.searchTicketsOrderByArrivingDate(startingCity, destinationCity, OrderBy.Desc)
            );
            case 9 -> exit();

        }

    }
}
