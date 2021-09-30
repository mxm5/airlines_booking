package Repositories.Impls;

import Base.Repository.Repository;
import Domain.Company;
import Domain.Ticket;
import Domain.enums.OrderBy;

import java.util.Comparator;
import java.util.List;

public class CompanyRepository extends Repository<Company, Long>  {
    @Override
    public Class<Company> getType() {
        return Company.class;
    }


}
