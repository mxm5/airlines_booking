package Repositories.Apis;

import Base.Repository.RepositoryApi;
import Base.Service.ServiceApi;
import Domain.Moderator;

public interface ModeratorRepositoryApi extends RepositoryApi<Moderator,Long> {

    Moderator existsModeratorWithUsernameAndPassWord(String username,String password );
}
