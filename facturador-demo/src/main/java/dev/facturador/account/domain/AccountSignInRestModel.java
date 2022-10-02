package dev.facturador.account.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public record AccountSignInRestModel(
        @NotEmpty @Length(min = 3) String usernameOrEmail,
        @NotEmpty @Length(min = 8, max = 40) String password) {
}
