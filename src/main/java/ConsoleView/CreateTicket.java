package ConsoleView;

import Base.View.View;
import Domain.Company;
import Domain.Ticket;
import Util.Context;

import java.time.LocalDateTime;

public class CreateTicket extends View {


    public CreateTicket() {

        Ticket ticket;
        LocalDateTime arrivingTime;
        LocalDateTime movingTime;
        String startingCity = enterValue("enter the starting city");
        String finalDestination = enterValue("enter the final destination ");
        Company company = Context.getCurrentModerator().getCompany();
        int price = setPrice();
        int count = selectOpt(300,"enter the passengers count ");
        try {
            movingTime = enterTicketDateTime();
            arrivingTime = enterTicketDateTime();
            ticket = new Ticket(
                    movingTime,
                    arrivingTime,
                    price,
                    company,
                    startingCity,
                    finalDestination
            );

            Context.getModeratorService().createATripWithTickets(
                    ticket, count
            );
            success("tickets created");
        } catch (Exception e) {
            e.printStackTrace();
            warning("failed creating tickets");
        }
        new ModeratorDashboard();
    }
}
