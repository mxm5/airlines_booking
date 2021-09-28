package Services.Impls;


import Base.Service.Service;
import Domain.Company;
import Repositories.Impls.CompanyRepository;

public class CompanyService extends Service<Company, Long, CompanyRepository> {

    public CompanyService(CompanyRepository repository) {
        super(repository);
    }
}
