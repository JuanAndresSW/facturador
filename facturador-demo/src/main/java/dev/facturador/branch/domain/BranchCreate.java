package dev.facturador.branch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.global.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class BranchCreate implements Serializable {
    public static final Long serialVersinUID = 1L;

    @JsonProperty(value = "IDTrader")
    @NotNull
    private long IDTrader;
    @Size(min = 3, max = 30)
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String phone;
    @Valid
    @NotNull
    private Address address;

    private String photo;
    private String logo;

    @Size(min = 7, max = 7)
    private String preferenceColor;
}
