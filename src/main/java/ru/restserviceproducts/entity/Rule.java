package ru.restserviceproducts.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rule")
@JsonIgnoreProperties({"isActive", "product", "dateCreate", "dateUpdate"})
public class Rule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "start_salary")
    private Long startSalary;

    @Column(name = "end_salary")
    private Long endSalary;

    @Column(name = "is_debt")
    private Boolean isDebt;

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

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public Long getStartSalary() { return startSalary; }

    public void setStartSalary(Long startSalary) { this.startSalary = startSalary; }

    public Long getEndSalary() { return endSalary; }

    public void setEndSalary(Long endSalary) { this.endSalary = endSalary; }

    public Boolean getIsDebt() { return isDebt; }

    public void setIsDebt(Boolean isDebt) { this.isDebt = isDebt; }

    public Date getDateCreate() { return dateCreate; }

    public void setDateCreate(Date dateCreate) { this.dateCreate = dateCreate; }

    public Date getDateUpdate() { return dateUpdate; }

    public void setDateUpdate(Date dateUpdate) { this.dateUpdate = dateUpdate; }

    public boolean getIsActive() { return isActive; }

    public void setIsActive(boolean active) { this.isActive = active; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", start_salary=" + startSalary +
                ", end_salary=" + endSalary +
                ", is_debt=" + isDebt +
                ", date_create=" + dateCreate +
                ", date_update=" + dateUpdate +
                ", is_active=" + isActive +
                ", product=" + product +
                '}';
    }
}
