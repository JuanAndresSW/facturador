package dev.facturador.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public record LoginDto(
        @NotEmpty @Length(min = 3) String usernameOrEmail,
        @NotEmpty @Length(min = 8, max = 40) String password) {
}
