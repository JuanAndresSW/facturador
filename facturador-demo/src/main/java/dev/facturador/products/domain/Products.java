package dev.facturador.products.domain;

import dev.facturador.branch.domain.Branch;
import dev.facturador.pointofsale.domain.PointOfSale;
import lombok.*;
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

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_branch", nullable = false, referencedColumnName = "id_branch")
    private Branch branch;
}