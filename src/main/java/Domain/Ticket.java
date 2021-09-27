package Domain;

import Base.BaseEntity;
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


    public Ticket(LocalDateTime movingDate, LocalDateTime arrivingDate, Company providerCompany) {
        this.movingDate = movingDate;
        this.arrivingDate = arrivingDate;
        this.providerCompany = providerCompany;
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

    @PrePersist
    public void prePersist() {
        this.orderingTime = LocalDateTime.now();
    }

//TODO  creating company to make

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
                "providerCompany = " + providerCompany + ")";
    }
}
