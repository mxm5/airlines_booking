package Util;

import Base.BaseEntity;
import Exceptions.TransactionIsOpen;

import javax.persistence.*;

public class DataBaseUtil {

    public static final EntityManagerFactory entityManagerFactory;
    public static final EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(Variables.persistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static <E extends BaseEntity> void simpleSave(E entity)   {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

    }

}
