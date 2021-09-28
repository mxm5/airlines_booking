package Services.Apis;

import Base.Service.ServiceApi;
import Domain.Company;
import Domain.Moderator;
import Domain.Ticket;

public interface ModeratorServiceApi extends ServiceApi<Moderator, Long> {
    Moderator loginModerator(String username, String password);//todo

    boolean registerModerator(Moderator moderator);
    //todo moderators must get role and comp from views

    boolean registerCompany(Company company);//todo

    void withdraw(Long withdraw,Company company);

    void createATripWithTickets(Ticket ticket,int count);// todo tickets time must be after now

    void createAssistantModerator();//todo

    void cancelTicketAndRefund();//todo

    void changeCompanyBrand();//todo

    void changePassword();//todo
}
