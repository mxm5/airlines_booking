package Services.Impls;


import Base.Service.Service;
import Domain.Company;
import Domain.Moderator;
import Domain.Ticket;
import Repositories.Impls.CompanyRepository;
import Repositories.Impls.ModeratorRepository;
import Repositories.Impls.TicketRepository;
import Services.Apis.ModeratorServiceApi;
import Util.Context;

import java.util.List;
import java.util.stream.IntStream;

public class ModeratorService extends Service<Moderator, Long, ModeratorRepository> implements ModeratorServiceApi {
    private final CompanyRepository companyRepository;
    private final TicketRepository ticketRepository;


    public ModeratorService(ModeratorRepository repository) {
        super(repository);
        ticketRepository = new TicketRepository();
        companyRepository = new CompanyRepository();
    }

    @Override
    public Moderator loginModerator(String username, String password) {
        return repository.existsModeratorWithUsernameAndPassWord(
                username,
                password);
    }

    @Override
    public boolean registerModerator(Moderator moderator) {
        try {
            repository.save(moderator);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean registerCompany(Company company) {
        Moderator currentModerator = Context.getCurrentModerator();
        if (currentModerator == null) return false;
        try {
            company.getModerators().add(
                    currentModerator
            );
            currentModerator.setCompany(company);
            repository.save(currentModerator);
            companyRepository.save(company);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void withdraw(Long withdraw, Company company) {
        //todo check role
        //todo check company balance
        //todo check transaction fee
    }

    @Override
    public List<Ticket> viewListOfCompanyTickets() {
        Company currentCompany = Context.getCurrentModerator().getCompany();
        if (currentCompany != null)
            return currentCompany.getTicketsProvided();
        else return null;
    }

    @Override
    public List<Ticket> viewListOfCompanyTicketsByDestination() {
//        return ticketRepository.getTicketsCountProvidedByTrip();
        return null;
    }

    @Override
    public void createATripWithTickets(Ticket ticket, int count) {
        final Ticket t = ticket;
        IntStream.range(0, count).forEach(
                (i) -> {
                    final Ticket newticket = new Ticket(
                            t.getMovingDate(),
                            t.getArrivingDate(),
                            t.getPrice(),
                            t.getProviderCompany(),
                            t.getHome(),
                            t.getDestination()
                    );
                    t.getProviderCompany().getTicketsProvided().add(newticket);
                    try {
                        ticketRepository.save(
                                newticket
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Override
    public void createAssistantModerator() {

    }

    @Override
    public void cancelTicketAndRefund() {

    }

    @Override
    public void changeCompanyBrand() {

    }

    @Override
    public void changePassword() {

    }
}
