package dev.facturador.global.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotEmpty
    private String province;
    @NotEmpty
    @Size(min = 4, max = 40)
    private String department;
    @NotEmpty
    @Size(min = 4, max = 40)
    private String locality;
    @NotEmpty
    private String postalCode;
    @NotEmpty
    @Size(min = 4, max = 40)
    private String street;
    @NotNull
    private int addressNumber;

}