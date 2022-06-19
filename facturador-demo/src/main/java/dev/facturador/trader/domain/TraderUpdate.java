package dev.facturador.trader.domain;

import org.hibernate.validator.constraints.Length;


public record TraderUpdate(
        @Length(min = 3, max = 20) String updatedBusinessName,
        String updatedVatCategory,
        @Length(min = 11, max = 15) String updatedCuit
) {
}
