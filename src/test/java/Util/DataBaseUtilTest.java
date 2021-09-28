package Util;

import Base.Entity.testEntity;
import Domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static Util.DataBaseUtil.*;
import static Util.FakerUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class DataBaseUtilTest {

    @Test
    void canPersistData() {
        testEntity testEntity = new testEntity();
        entityManager.getTransaction().begin();
        entityManager.persist(testEntity);
        entityManager.getTransaction().commit();
        Long id = testEntity.getId();
        assertNotNull(id);
        testEntity testEntityQ = entityManager.find(testEntity.class, id);
        assertNotNull(testEntityQ);
        entityManager.remove(testEntityQ);
        testEntityQ = entityManager.find(testEntity.class, id);
        assertNull(testEntityQ);
    }

    @Test
    void shouldSimplySave() throws Exception {
        testEntity testEntity = new testEntity();
        System.out.println("========================"+testEntity.getId());
        simpleSave(testEntity);
        Long id = testEntity.getId();
        System.out.println("========================"+id);
        assertNotNull(id);
        testEntity testEntityQ = entityManager.find(
                testEntity.class,
                id);
        assertNotNull(testEntityQ);
        assertEquals(testEntityQ,testEntity);
        //
        // remove
        //
        //
        entityManager.remove(testEntityQ);
        testEntityQ = entityManager.find(
                testEntity.class,
                id);
        assertNull(testEntityQ);
    }

    @Test
    void find() {
        Customer customer = fakeCustomer();
        simpleSave(customer);
        Customer customerDetached = new Customer();
        customerDetached.setFirstName(customer.getFirstName());
        Executable executable = () -> entityManager.find(Customer.class, customerDetached);
        assertThrows(Exception.class, executable);

    }
}