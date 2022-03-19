package dev.facturador.mainaccount.domain.vo;

import org.hibernate.validator.constraints.Length;


public record TraderUpdate(
        @Length(min = 3, max = 20) String newBusinessName,
        String newVatCategory,
        @Length(min = 11, max = 15) String newCode
) {
}
