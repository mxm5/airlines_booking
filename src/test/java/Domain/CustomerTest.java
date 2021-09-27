package Domain;

import Util.DataBaseUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void createACustomer() {

        Customer customer = new Customer(
                "moh",
                "mohammadi",
                "mxmxm",
                "123");
        assertNull(customer.getId());
        DataBaseUtil.simpleSave(customer);
        assertNotNull(customer.getId());
    }
}