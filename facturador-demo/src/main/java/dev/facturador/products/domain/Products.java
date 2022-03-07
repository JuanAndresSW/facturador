package dev.facturador.products.domain;

import dev.facturador.pointofsale.domain.PointOfSale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Products {

    @Id
    @Column(name = "id_products")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduct;

    @Column(name = "quantity", nullable = false)
    private int quantityProducts;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "price", nullable = false, scale = 2)
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_point_of_sale", nullable = false, referencedColumnName = "id_point_of_sale")
    private PointOfSale pointOfSaleOwnerProduct;
}