package dev.facturador.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Dto del Json para el usuario
 */
@Data
public final class UserDto {
    @NotEmpty(message = "NotEmpity")
    @Length(min = 3, max = 20, message = "Invalid")
    private String username;
    @Email(message = "Invalid")
    private String email;
    @NotEmpty(message = "NotEmpity")
    @Length(min = 8, max = 40, message = "Invalid")
    private String password;

    private String avatar;

}
