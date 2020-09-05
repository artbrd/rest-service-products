package ru.restserviceproducts.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "rules_rpoduct")
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
public class RuleProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "start_salary")
    private Long start_salary;

    @Column(name = "end_salary")
    private Long end_salary;

    @Column(name = "is_debt")
    private Boolean is_debt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    @Column(name = "date_create")
    @CreatedDate
    private Date date_create;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    @Column(name = "date_update")
    @LastModifiedDate
    private Date date_update;

    @Column(name = "is_active")
    private boolean is_active;

    @ManyToMany(mappedBy = "rules", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Product> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getStart_salary() {
        return start_salary;
    }

    public void setStart_salary(Long start_salary) {
        this.start_salary = start_salary;
    }

    public Long getEnd_salary() {
        return end_salary;
    }

    public void setEnd_salary(Long end_salary) {
        this.end_salary = end_salary;
    }

    public Boolean getIs_debt() {
        return is_debt;
    }

    public void setIs_debt(Boolean is_debt) {
        this.is_debt = is_debt;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "RuleProduct{" +
                "id=" + id +
                ", start_salary=" + start_salary +
                ", end_salary=" + end_salary +
                ", is_debt=" + is_debt +
                ", date_create=" + date_create +
                ", date_update=" + date_update +
                ", is_active=" + is_active +
                ", products=" + products +
                '}';
    }
}
