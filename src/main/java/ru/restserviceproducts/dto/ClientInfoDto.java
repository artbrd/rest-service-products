package ru.restserviceproducts.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientInfoDto {
    @NotNull
    private Long salary;
    @NotNull
    private Long claim;
    private Boolean isDebtor;
}
