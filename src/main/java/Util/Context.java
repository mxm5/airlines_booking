package Util;

import Domain.Customer;
import Domain.Moderator;
import Repositories.Impls.CustomerRepository;
import Repositories.Impls.ModeratorRepository;
import Services.Apis.CustomerServiceApi;
import Services.Apis.ModeratorServiceApi;
import Services.Impls.CustomerService;
import Services.Impls.ModeratorService;
import lombok.Getter;
import lombok.Setter;


public class Context {
    private static CustomerServiceApi customerService;
    private static ModeratorServiceApi moderatorService;


   private static Customer currentCustomer;

    private static Moderator currentModerator;

   static {
        customerService = new CustomerService(
                new CustomerRepository()
        );
        moderatorService = new ModeratorService(
                new ModeratorRepository()
        );
    }

    public static CustomerServiceApi getCustomerService() {
        return customerService;
    }

    public static void setCustomerService(CustomerServiceApi customerService) {
        Context.customerService = customerService;
    }

    public static ModeratorServiceApi getModeratorService() {
        return moderatorService;
    }

    public static void setModeratorService(ModeratorServiceApi moderatorService) {
        Context.moderatorService = moderatorService;
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer currentCustomer) {
        Context.currentCustomer = currentCustomer;
    }

    public static Moderator getCurrentModerator() {
        return currentModerator;
    }

    public static void setCurrentModerator(Moderator currentModerator) {
        Context.currentModerator = currentModerator;
    }
}
