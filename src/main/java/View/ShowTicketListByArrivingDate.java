package View;

import Base.View.View;
import Domain.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowTicketListByArrivingDate extends View {
    private final List<Ticket> ticketData;

    public ShowTicketListByArrivingDate(List<Ticket> ticketss) {
        this.ticketData = ticketss;
        Map<LocalDateTime, List<Ticket>> map = ticketData.stream().collect(Collectors.groupingBy(Ticket::getArrivingDate));


        line(50);
        for (LocalDateTime key : map.keySet()) {
            List<Ticket> tickets = map.get(key);
            Ticket firstTicket = null;
            if (tickets.size() != 0) {

                firstTicket = tickets.get(0);
            }
            assert firstTicket != null;
            printTicketInFormat(firstTicket);
            System.out.println();
            System.out.println(" arriving date  " + firstTicket.getMovingDate());
            System.out.println(" x " + tickets.size());

        }
   new CustomerDashboard();
    }
}
