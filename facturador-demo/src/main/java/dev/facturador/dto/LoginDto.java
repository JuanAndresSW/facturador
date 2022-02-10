package dev.facturador.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {
    @NotEmpty
    @Length(min = 3)
    private String usernameOrEmail;

    @NotEmpty
    @Length(min = 8, max = 40)
    private String password;
}
