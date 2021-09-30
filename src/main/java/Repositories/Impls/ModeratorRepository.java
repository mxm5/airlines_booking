package Repositories.Impls;

import Base.Repository.Repository;
import Domain.Moderator;
import Repositories.Apis.ModeratorRepositoryApi;

import javax.persistence.TypedQuery;

public class ModeratorRepository extends Repository<Moderator, Long> implements ModeratorRepositoryApi {
    @Override
    public Class<Moderator> getType() {
        return Moderator.class;
    }

    public Moderator existsModeratorWithUsernameAndPassWord(String username, String password) {

        TypedQuery<Moderator> query = getEntityManager().createQuery(
                " FROM " + getType().getSimpleName() + " u WHERE u.userName = '" + username + "' " +
                        "AND u.password = '" + password + "' "
                , getType());
        try {
            return query.getSingleResult();

        } catch (Exception e) {
            System.out.println("not found");
            return null;
        }
    }


}
