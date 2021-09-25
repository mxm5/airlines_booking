package Util;

import Domain.BaseEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseUtilTest {

    @Test
    void canPersistData() {
        BaseEntity baseEntity = new BaseEntity();
        DataBaseUtil.entityManager.getTransaction().begin();
        DataBaseUtil.entityManager.persist(baseEntity);
        DataBaseUtil.entityManager.getTransaction().commit();
        Long id = baseEntity.getId();
        assertNotNull(id);
        BaseEntity baseEntityQ = DataBaseUtil.entityManager.find(BaseEntity.class, id);
        assertNotNull(baseEntityQ);
        DataBaseUtil.entityManager.remove(baseEntityQ);
        baseEntityQ = DataBaseUtil.entityManager.find(BaseEntity.class, id);
        assertNull(baseEntityQ);
    }
}