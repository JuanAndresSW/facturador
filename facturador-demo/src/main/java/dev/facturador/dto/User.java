package dev.facturador.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dto del Json para el usuario
 */
@Data
public final class User {

    @NotNull(message = "Cannot be null")
    private String username;

    @NotNull(message = "Cannot be null")
    @Email
    private String email;

    @NotNull(message = "Cannot be null")
    @Size(min = 8, max = 40, message = "invalid size")
    private String password;

    private String avatar;

}
