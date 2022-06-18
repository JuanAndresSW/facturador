package dev.facturador.user.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public record UserUpdate(
        @NotEmpty @Length(min = 3, max = 20) String username,
        @Length(min = 3, max = 20) String updatedUsername,
        @Length(min = 8, max = 40) String password,
        @Length(min = 8, max = 40) String updatedPassword,
        String updatedAvatar
) {
}
