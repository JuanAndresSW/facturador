package dev.facturador.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Dto del Json para el usuario
 */

public record UserDto(
        @NotEmpty(message = "NotEmpity") @Length(min = 3, max = 20) String username,
        @Email(message = "email-In") String email,
        @NotEmpty(message = "NotEmpity") @Length(min = 8, max = 40) String password,
        String avatar
) {
}
