package Domain;

import Base.Entity.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends Person {

    public Customer(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
    }

    public void addBalance(Integer amount) {
        this.balance+=amount;
    }
  public   void subBalance(Integer amount){
        this.balance-=amount;
    }

    // R 1-1 ticket
    // A balance
    @Column(name = "balance", nullable = false)
    private Long balance=0L;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Ticket> tickets=new ArrayList<>();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,balance, tickets);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "userName = " + userName + ", " +
                "balance = " + balance + ")";
    }
}
