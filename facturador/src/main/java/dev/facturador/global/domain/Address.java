package dev.facturador.global.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    @NotEmpty
    private String province;
    @NotEmpty
    @Size(min = 4, max = 40)
    private String city;
    @NotEmpty
    private String postalCode;
    @NotEmpty
    @Size(min = 4, max = 40)
    private String street;
    @NotNull
    private int addressNumber;

}