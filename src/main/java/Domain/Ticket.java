package Domain;

import Base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    @Column(name = "moving_date", nullable = false)
    private LocalDateTime movingDate;

    @Column(name = "arriving_date", nullable = false)
    private LocalDateTime arrivingDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private Customer owner;

    @Column(name = "ordering_time", nullable = false)
    private String orderingTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "provider_company_id")
    private Company providerCompany;

//TODO  creating company to make

}
