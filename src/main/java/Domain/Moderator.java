package Domain;

import Base.Person;
import Util.TimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Moderator extends Person {
//R company
//A joinDate
//C authority


    @PrePersist
    public void prePersist() {
        this.joinDate = TimeUtil.nowToSqlDate();
    }

    public Moderator(String firstName, String lastName, String userName, String password, Roles authority) {
        super(firstName, lastName, userName, password);
        this.authority = authority;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "join_date", nullable = false)
    private Date joinDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false, unique = true)
    private Roles authority;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Moderator moderator = (Moderator) o;
        return Objects.equals(id, moderator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,company, joinDate, authority);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "userName = " + userName + ", " +
                "company = " + company + ", " +
                "joinDate = " + joinDate + ", " +
                "authority = " + authority + ")";
    }
}

