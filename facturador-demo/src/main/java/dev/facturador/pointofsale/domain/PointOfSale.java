package dev.facturador.pointofsale.domain;

import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("ALL")
@Entity
@Table(name = "point_of_sale")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class PointOfSale implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Id
    @Column(name = "id_point_of_sale")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPointOfSale;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "email", nullable = false, length = 128)
    private String email;
    @Column(name = "province", nullable = false, length = 20)
    private String province;
    @Column(name = "department", nullable = false, length = 45)
    private String department;
    @Column(name = "locality", nullable = false, length = 45)
    private String locality;
    @Column(name = "postalCode", nullable = false, length = 10)
    private String postalCode;
    @Column(name = "street", nullable = false, length = 50)
    private String street;
    @Column(name = "numberAdress", nullable = false, length = 5)
    private String numberAdress;
    @Column(name = "height", nullable = false, length = 5)
    private String floor;
    @Column(name = "unit", nullable = false, length = 5)
    private String unit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trader", nullable = false)
    private Trader traderOwner;

    public static PointOfSale create(PointOfSaleCreate valuePointOfSale, Trader trader){
        var pointOfSale = new PointOfSale();

        pointOfSale.setName(valuePointOfSale.getName());
        pointOfSale.setEmail(valuePointOfSale.getEmail());

        pointOfSale.setProvince(valuePointOfSale.getAdress().getProvince());
        pointOfSale.setDepartment(valuePointOfSale.getAdress().getDepartment());
        pointOfSale.setLocality(valuePointOfSale.getAdress().getLocality());
        pointOfSale.setPostalCode(valuePointOfSale.getAdress().getPostalCode());
        pointOfSale.setStreet(valuePointOfSale.getAdress().getStreet());
        pointOfSale.setNumberAdress(valuePointOfSale.getAdress().getNumberAdress());
        pointOfSale.setFloor(valuePointOfSale.getAdress().getFloor());
        pointOfSale.setUnit(valuePointOfSale.getAdress().getUnit());

        pointOfSale.setTraderOwner(trader);

        return pointOfSale;
    }
}