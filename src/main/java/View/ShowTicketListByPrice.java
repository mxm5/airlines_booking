package View;

import Base.View.View;
import Domain.Ticket;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShowTicketListByPrice extends View {
    private List<Ticket> ticketData;

    public ShowTicketListByPrice(List<Ticket> ticketData) {
        this.ticketData = ticketData;
        Map<Integer, List<Ticket>> map = ticketData.stream().collect(Collectors.groupingBy(Ticket::getPrice));


        line(50);
        for (Integer key : map.keySet()) {
            List<Ticket> tickets = map.get(key);
            Ticket firstTicket = null;
            if (tickets.size() != 0) {
                firstTicket = tickets.get(0);
            }
            assert firstTicket != null;
            printTicketInFormat(firstTicket);
            System.out.println();
            System.out.println("price " + firstTicket.getPrice());
            System.out.println(" x " + tickets.size());

        }
        new CustomerDashboard();

    }

}
