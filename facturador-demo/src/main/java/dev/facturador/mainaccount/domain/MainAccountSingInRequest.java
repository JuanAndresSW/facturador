package dev.facturador.mainaccount.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public record MainAccountSingInRequest(
        @NotEmpty @Length(min = 3) String usernameOrEmail,
        @NotEmpty @Length(min = 8, max = 40) String password) {
}
