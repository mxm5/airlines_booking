package Domain;

import Base.Entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor

@Setter
public class Company extends BaseEntity<Long> {



    //R Moderators
    //R Tickets
    //C brandName
    //A balance
    public Company(String brandName) {
        this.brandName = brandName;


    }

    @OrderBy("joinDate desc")
    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @ToString.Exclude
    private List<Moderator> moderators=new ArrayList<>();

    @OrderBy("orderingTime")
    @OneToMany(mappedBy = "providerCompany", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @ToString.Exclude
    private List<Ticket> ticketsProvided=new ArrayList<>();

    @Column(name = "brand_name", nullable = false, unique = true)
    private String brandName;

    @Column(name = "balance", nullable = false)
    private Long balance = 0L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,moderators, ticketsProvided, brandName, balance);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "brandName = " + brandName + ", " +
                "balance = " + balance + ")";
    }
}
