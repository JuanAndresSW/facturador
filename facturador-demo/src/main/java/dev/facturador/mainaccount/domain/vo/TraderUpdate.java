package dev.facturador.mainaccount.domain.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public record TraderUpdate(
        @NotEmpty @Length(min = 3, max = 20) String businessName,
        @NotEmpty String vatCategory,
        @NotEmpty @Length(min = 11, max = 15) String newCode
) {
}
