package dev.facturador.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Dto del Json para el Comerciante
 */
public record TraderDto(
        @NotEmpty(message = "NotEmpity") @Length(min = 3, max = 20) String businessName,
        @NotEmpty(message = "NotEmpity") String vatCategory,
        @NotEmpty(message = "NotEmpity") @Length(min = 11, max = 15) String code,
        @NotEmpty(message = "NotEmpity") @Length(max = 15) String grossIncome
) {
}
