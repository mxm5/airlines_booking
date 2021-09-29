package Repositories.Impls;

import Base.Repository.Repository;
import Domain.Company;
import Domain.enums.OrderBy;

import java.util.Comparator;
import java.util.List;

public class CompanyRepository extends Repository<Company, Long>  {
    @Override
    public Class<Company> getType() {
        return Company.class;
    }

//
//    @Override
//    public List<Company> sortTicketsByCompanyBrand(OrderBy orderBy) {
//        List<Company> sortTicketsByCompanyBrand;
//        if (orderBy == OrderBy.Asc)
//
//            sortTicketsByCompanyBrand = getAll().stream().sorted(
//                    Comparator.comparing(
//                            Company::getBrandName
//                    )).toList();
//        else
//            sortTicketsByCompanyBrand = getAll().stream().sorted(
//                    Comparator.comparing(
//                            Company::getBrandName
//                    ).reversed()).toList();
//        return sortTicketsByCompanyBrand;
//    }
}
