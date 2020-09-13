package ru.restserviceproducts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_salary")
    private Long startSalary;

    @Column(name = "end_salary")
    private Long endSalary;

    @Column(name = "is_debt")
    private Boolean isDebt;

    @CreationTimestamp
    @Column(name = "date_create")
    private Date dateCreate;

    @UpdateTimestamp
    @Column(name = "date_update")
    private Date dateUpdate;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @PrePersist
    public void setDefaultActive() {
        if(getActive() == null) {
            setActive(true);
        }
    }
}
