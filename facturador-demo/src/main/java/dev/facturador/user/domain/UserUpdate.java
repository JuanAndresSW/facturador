package dev.facturador.user.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public record UserUpdate(
        @NotEmpty @Length(min = 3, max = 20, message = "Debe contener de 3-20 caracteres") String username,
        @Length(max = 20, message = "Maximo 20 caracteres") String updatedUsername,
        @Length(max = 40, message = "Maximo 40 caracteres") String password,
        @Length(max = 40, message = "Maximo 40 caracteres") String updatedPassword,
        String updatedAvatar
) {
}
