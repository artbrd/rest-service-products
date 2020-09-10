package ru.restserviceproducts.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"isActive", "rules", "dateCreate", "dateUpdate"})
//@Data
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_sum_cred")
    private Long startSumCred;

    @Column(name = "end_sum_cred")
    private Long endSumCred;

    @Column(name = "percent")
    private long percent;

    @Column(name = "term")
    private int term;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    @CreationTimestamp
    @Column(name = "date_create")
    private Date dateCreate;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    @UpdateTimestamp
    @Column(name = "date_update")
    private Date dateUpdate;

    @Column(name = "is_active")
    private boolean isActive = true;

//    @JsonIgnore
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Rule> rules;

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

    public Long getStartSumCred() {
        return startSumCred;
    }

    public void setStartSumCred(Long startSumCred) {
        this.startSumCred = startSumCred;
    }

    public Long getEndSumCred() {
        return endSumCred;
    }

    public void setEndSumCred(Long endSumCred) {
        this.endSumCred = endSumCred;
    }

    public long getPercent() {
        return percent;
    }

    public void setPercent(long percent) {
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

    public boolean getIsActive() { return isActive; }

    public void setIsActive(boolean active) { isActive = active; }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
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
