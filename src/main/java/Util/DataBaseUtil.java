package Util;

import Exceptions.TransactionIsOpen;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataBaseUtil {

    public static final EntityManagerFactory entityManagerFactory;
    public static final EntityManager entityManager;
    static {
         entityManagerFactory = Persistence.createEntityManagerFactory(Variables.persistenceUnitName);
         entityManager = entityManagerFactory.createEntityManager();
    }


}
