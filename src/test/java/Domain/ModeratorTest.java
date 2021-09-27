package Domain;

import Util.DataBaseUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeratorTest {
    @Test
    void saveModeratorInDataBase() {
        // Todo make a creation date for all entities
        Moderator moderator = new Moderator(
                "f",
                "l",
                "fl",
                "123",
                Roles.highAuthority);

        Company a = new Company("a");
        moderator.setCompany(a);
        DataBaseUtil.simpleSave(moderator);
        System.out.println(moderator);
        System.out.println(a);
        assertNotNull(a.getId());
        assertNotNull(moderator.getId());
        assertNotNull(moderator.getCompany());

    }
}