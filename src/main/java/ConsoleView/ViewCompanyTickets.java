package ConsoleView;

import Base.View.View;
import Domain.Ticket;
import Util.Context;

import java.util.List;

public class ViewCompanyTickets extends View {
    public ViewCompanyTickets() {
        List<Ticket> tickets = Context.getModeratorService().viewListOfCompanyTickets();
        line(50);
        tickets.forEach(
                this::printTicketInFormat
        );
        new ModeratorDashboard();

    }
}
