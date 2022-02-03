package dev.facturador.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dto del Json para el usuario
 */
@Data
public final class User {

    @NotNull
    @Size(min = 4, max = 30)
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;

    private String avatar;

}
