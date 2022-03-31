package dev.facturador.trader.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public record TraderRegister(
        @NotEmpty @Length(min = 3, max = 20) String businessName,
        @NotEmpty String vatCategory,
        @NotEmpty @Length(min = 11, max = 15) String code,
        @NotEmpty @Length(max = 15) String grossIncome
) {
}
