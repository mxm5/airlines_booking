package Domain;

import Util.DataBaseUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void createACompany() {
        DataBaseUtil.entityManager.getTransaction().begin();
        Company company = new Company("ni");

        System.out.println(company.toString());

        DataBaseUtil.entityManager.persist(company);
        DataBaseUtil.entityManager.getTransaction().commit();
        Long id = company.getId();

        Company company1 = DataBaseUtil.entityManager.find(Company.class, id);
        assertEquals(company,company1);



    }

    @Test
    void companyMustHaveEmployee(){

    }
}