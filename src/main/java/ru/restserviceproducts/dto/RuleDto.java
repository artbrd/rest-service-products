package ru.restserviceproducts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.restserviceproducts.entity.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"active", "product", "dateCreate", "dateUpdate"})
public class RuleDto {
    @Null
    private Long id;
    @NotNull
    private Long startSalary;
    @NotNull
    private Long endSalary;
    private Boolean isDebt;
    @Null
    private Date dateCreate;
    @Null
    private Date dateUpdate;
    @Null
    private Boolean active;
    @Null
    private Product product;
}
