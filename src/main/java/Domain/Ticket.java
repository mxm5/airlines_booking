package Domain;

import Base.Entity.BaseEntity;
import Exceptions.HomeAndDestinationAreSame;
import Exceptions.MovingDateCantBeAfterArriving;
import Exceptions.MovingDateCantBeInPastTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket extends BaseEntity<Long> {

//movingDate
//arrivingDate
//owner
//orderingTime
//providerCompany
//todo destination
//todo home

    public Ticket(
            LocalDateTime movingDate,
            LocalDateTime arrivingDate,
            Integer price,
            Company providerCompany,
            String home,
            String destination) {
        this.movingDate = movingDate;
        this.arrivingDate = arrivingDate;
        this.providerCompany = providerCompany;
        this.price = price;
        this.home=home;
        this.destination= destination;
    }

    @Column(name = "moving_date", nullable = false)
    private LocalDateTime movingDate;

    @Column(name = "arriving_date", nullable = false)
    private LocalDateTime arrivingDate;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private Customer owner;

    @Column(name = "ordering_time", nullable = false)
    private LocalDateTime orderingTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "provider_company_id", nullable = false)
    private Company providerCompany;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "home", nullable = false)
    private String home;

    @Column(name = "destination", nullable = false)
    private String destination;

    @PrePersist
    public void prePersist() throws RuntimeException {
        if(home.equals(destination)) {
            HomeAndDestinationAreSame err = new HomeAndDestinationAreSame(
                    "home and destination cant be same");
            err.printStackTrace();
            //            throw err;
        }
        this.orderingTime = LocalDateTime.now();
        if (movingDate.isAfter(arrivingDate)) {
            MovingDateCantBeAfterArriving err = new MovingDateCantBeAfterArriving(
                    "moving date cant be after arriving date"
            );
            err.printStackTrace();
//            throw err;
                    }
        if (movingDate.isBefore(LocalDateTime.now())) {

            MovingDateCantBeInPastTime err = new MovingDateCantBeInPastTime(
                    "moving Date cant be in past time"
            );
            err.printStackTrace();
//            throw err;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id,movingDate, arrivingDate, owner, orderingTime, providerCompany);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "movingDate = " + movingDate + ", " +
                "arrivingDate = " + arrivingDate + ", " +
                "owner = " + owner + ", " +
                "orderingTime = " + orderingTime + ", " +
                "providerCompany = " + providerCompany + ", " +
                "price = " + price + ", " +
                "home = " + home + ", " +
                "destination = " + destination + ")";
    }
}
