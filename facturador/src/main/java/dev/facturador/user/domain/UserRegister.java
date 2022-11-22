package dev.facturador.user.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record UserRegister(
        @NotEmpty @Length(min = 3, max = 20) String username,
        @Email String email,
        @NotEmpty @Length(min = 8, max = 40) String password,
        String avatar
) {
}