package Repositories.Impls;

import Domain.Moderator;
import Util.FakerUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeratorRepositoryTest {

    ModeratorRepository moderatorRepository = new ModeratorRepository();


    @Test
    void existsModeratorWithUsernameAndPassWord() throws Exception {
        Moderator moderator = FakerUtil.fakeModerator();
        moderatorRepository.save(moderator);
        System.out.println(moderator);
        Moderator loggedIn = moderatorRepository.existsModeratorWithUsernameAndPassWord(
                moderator.getUserName(),
                moderator.getPassword());
        assertEquals(moderator,loggedIn);
    }
}