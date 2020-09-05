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
@Table(name = "products")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_sum_cred")
    private Double startSumCred;

    @Column(name = "end_sum_cred")
    private Double endSumCred;

    @Column(name = "percent")
    private double percent;

    @Column(name = "term")
    private int term;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    //@DateTimeFormat(pattern="dd-mm-yyyy")
    @Column(name = "date_create")
    @CreatedDate
    private Date dateCreate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    //@DateTimeFormat(pattern="dd-mm-yyyy")
    @Column(name = "date_update")
    @LastModifiedDate
    private Date dateUpdate;

    @Column(name = "is_active")
    private boolean isActive;

    //@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_product_rules",
            joinColumns =  @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    //@JsonIgnore
    private Set<RuleProduct> rules;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStartSumCred() {
        return startSumCred;
    }

    public void setStartSumCred(Double startSumCred) {
        this.startSumCred = startSumCred;
    }

    public Double getEndSumCred() {
        return endSumCred;
    }

    public void setEndSumCred(Double endSumCred) {
        this.endSumCred = endSumCred;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<RuleProduct> getRules() {
        return rules;
    }

    public void setRules(Set<RuleProduct> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startSumCred=" + startSumCred +
                ", endSumCred=" + endSumCred +
                ", percent=" + percent +
                ", term=" + term +
                ", dateCreate=" + dateCreate +
                ", dateUpdate=" + dateUpdate +
                ", isActive=" + isActive +
                ", rules=" + rules +
                '}';
    }
}