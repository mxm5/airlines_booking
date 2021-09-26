package Domain;

import Base.Person;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends Person {


    // R 1-1 ticket
    // A balance
    @Column(name = "balance", nullable = false)
    private Long balance;

    @OrderBy("arrivingDate desc")
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Ticket> tickets;

}
