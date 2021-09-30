package Base.View;

import Domain.Ticket;
import Util.FakerUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {

    @Test
    void selectOpt() {
//        View view = new View();
//        int opt = view.selectOpt(200);
    }

    @Test
    void printTicketInFormat() {
        Ticket ticket = FakerUtil.fakeTicket();

        new View().printTicketInFormat(ticket);
    }
}