package dev.facturador.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Dto del Json para el Comerciante
 */
@Data
public final class TraderDto {
    @NotEmpty(message = "NotEmpity")
    @Length(min = 3, max = 20, message = "Invalid")
    private String businessName;
    @NotEmpty(message = "NotEmpity")
    private String vatCategory;
    @NotEmpty(message = "NotEmpity")
    @Length(min = 11, max = 15, message = "Invalid")
    private String code;
    @NotEmpty
    @Length(max = 15, message = "Invalid")
    private String grossIncome;
}
