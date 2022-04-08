package dev.facturador.pointofsale.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "point_of_sale")
@NoArgsConstructor
@Getter
@Setter
public class PointOfSale {
    @Id
    @Column(name = "id_point_of_sale")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointOfSaleId;

    @Column(name = "point_of_sale_number", nullable = false)
    private int pointOfSaleNumber;
    @Column(name = "floor", nullable = false, length = 5)
    private String floor;
    @Column(name = "unit", nullable = false, length = 5)
    private String unit;
}
