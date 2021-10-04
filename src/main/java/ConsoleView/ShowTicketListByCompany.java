package ConsoleView;

import Base.View.View;
import Domain.Company;
import Domain.Ticket;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowTicketListByCompany extends View {
    private final List<Ticket> ticketData;

    public ShowTicketListByCompany(List<Ticket> ticketss) {
        this.ticketData = ticketss;
        Map<Company, List<Ticket>> map = ticketData.stream().collect(Collectors.groupingBy(Ticket::getProviderCompany));


        line(50);
        for (Company key : map.keySet()) {
            List<Ticket> tickets = map.get(key);
            Ticket firstTicket = null;
            if (tickets.size() != 0) {

                firstTicket = tickets.get(0);
            }
            assert firstTicket != null;
            printTicketInFormat(firstTicket);
            System.out.println();
            System.out.println("from Company " + firstTicket.getProviderCompany().getBrandName());
            System.out.println(" x " + tickets.size());

        }
        new CustomerDashboard();
    }
}
