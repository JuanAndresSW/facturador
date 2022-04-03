package dev.facturador.pointofsale.domain;

import dev.facturador.mainaccount.domain.MainAccountRegister;
import dev.facturador.trader.domain.TraderRegister;
import dev.facturador.user.domain.UserRegister;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor
public final class PointOfSaleCreate {
    @NotEmpty
    private String username;
    @Length(min = 3, max = 20)
    @NotEmpty
    private String name;
    @NotNull
    private PointOfSaleAdress adress;
    @NotEmpty
    @Email
    private String email;

    private String logo;
    @Length(min = 7, max = 7)
    private String color;

    public PointOfSaleCreate(String username, String name, String email, String logo, String color){
        this.username = username;
        this.name = name;
        this.email = email;
        this.logo = logo;
        this.color = color;
    }

    public static PointOfSaleCreate starter
            (String username, String name, String email, String logo, String color) {
        return new PointOfSaleCreate(username, name, email, logo, color);
    }

    public PointOfSaleCreate setAddress
            (PointOfSaleAdress adress) {
        this.setAdress(adress);
        return this;
    }
}
