package Domain;

import Util.DataBaseUtil;
import Util.Variables;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static Util.DataBaseUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void createACompany() {

        Company company = new Company("1321321");

        System.out.println(company.toString());


        simpleSave(company);

        Long id = company.getId();

        Company company1 = DataBaseUtil.entityManager.find(Company.class, id);
        assertEquals(company,company1);



    }

    @Test
    void companyMustHaveEmployee(){

    }
}