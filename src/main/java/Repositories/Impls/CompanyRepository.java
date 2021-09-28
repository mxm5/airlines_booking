package Repositories.Impls;

import Base.Repository.Repository;
import Domain.Company;
import Domain.Customer;
import Repositories.Apis.CustomerRepositoryApi;

public class CompanyRepository extends Repository<Company,Long>  {
    @Override
    public Class<Company> getType() {
        return Company.class;
    }




}
