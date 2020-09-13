package ru.restserviceproducts.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @CreationTimestamp
    @Column(name = "date_create")
    private Date dateCreate;

    @UpdateTimestamp
    @Column(name = "date_update")
    private Date dateUpdate;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Rule> rules;

    @PrePersist
    public void setDefaultActive() {
        if(getActive() == null) {
            setActive(true);
        }
    }
}
