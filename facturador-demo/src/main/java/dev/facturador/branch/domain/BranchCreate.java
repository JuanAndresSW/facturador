package dev.facturador.branch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public final class BranchCreate {
    @NotNull
    private long IDTrader;
    @Length(min = 3, max = 30)
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String phone;

    @NotNull
    private BranchAddress address;

    private String photo;
    private String logo;

    @Length(min = 7, max = 7)
    private String color;
}
