package dev.facturador.branch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchAddress {
    @NotEmpty
    private String province;
    @NotEmpty
    private String department;
    @NotEmpty
    private String locality;
    @NotEmpty
    private String postalCode;
    @NotEmpty
    private String street;
    @NotEmpty
    private int numberAdress;

}