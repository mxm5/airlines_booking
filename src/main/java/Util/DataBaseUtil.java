package Util;

import Base.Entity.BaseEntity;

import javax.persistence.*;

public class DataBaseUtil {

    public static final EntityManagerFactory entityManagerFactory;
    public static final EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(Variables.persistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static <E extends BaseEntity> void simpleSave(E entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

    }

    public static <E extends BaseEntity> void simpleUpdateInTx(E entity) {
        if (entity.getId() == null)
            entityManager.persist(entity);
        else
            entityManager.merge(entity);
    }
}
