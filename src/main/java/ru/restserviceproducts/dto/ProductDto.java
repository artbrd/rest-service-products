package ru.restserviceproducts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.restserviceproducts.entity.Rule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"active", "rules", "dateCreate", "dateUpdate"})
public class ProductDto {
    @Null
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Long startSumCred;
    @NotNull
    private Long endSumCred;
    @NotNull
    private long percent;
    @NotNull
    private int term;
    @Null
    private Date dateCreate;
    @Null
    private Date dateUpdate;
    @Null
    private Boolean active;
    @Null
    private Set<Rule> rules;
}
