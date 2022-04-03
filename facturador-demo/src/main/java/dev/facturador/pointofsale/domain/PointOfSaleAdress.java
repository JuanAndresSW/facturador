package dev.facturador.pointofsale.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor
public class PointOfSaleAdress {
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
    private String numberAdress;
    @NotEmpty
    private String floor;
    @NotEmpty
    private String unit;

    public static PointOfSaleAdress starter
            (String province, String department, String locality, String postalCode, String street, String numberAdress, String floor, String unit) {
        return new PointOfSaleAdress
                (province, department, locality, postalCode, street, numberAdress, floor, unit);
    }
}