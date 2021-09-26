package Util;

import Base.testEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseUtilTest {

    @Test
    void canPersistData() {
        testEntity testEntity = new testEntity();
        DataBaseUtil.entityManager.getTransaction().begin();
        DataBaseUtil.entityManager.persist(testEntity);
        DataBaseUtil.entityManager.getTransaction().commit();
        Long id = testEntity.getId();
        assertNotNull(id);
        testEntity testEntityQ = DataBaseUtil.entityManager.find(testEntity.class, id);
        assertNotNull(testEntityQ);
        DataBaseUtil.entityManager.remove(testEntityQ);
        testEntityQ = DataBaseUtil.entityManager.find(testEntity.class, id);
        assertNull(testEntityQ);
    }
}