package dev.facturador.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public final class LoginDto {
    @NotEmpty
    private String usernameOrEmail;
    @NotEmpty
    @Length(min = 8, max = 40)
    private String password;
}
