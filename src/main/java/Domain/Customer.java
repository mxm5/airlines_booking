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

    public Customer(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
    }

    // R 1-1 ticket
    // A balance
    @Column(name = "balance", nullable = false)
    private Long balance=0L;

    @OrderBy("arrivingDate desc")
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Ticket> tickets;

}
