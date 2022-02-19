package dev.facturador.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "productos")
@NoArgsConstructor @Getter @Setter @ToString
public final class Productos {

    @Id
    @Column(name = "id_productos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduct;

    @Column(name = "cantidad", nullable = false)
    private int amountProducts;

    @Column(name = "nombre", nullable = false, length = 50)
    private String name;

    @Column(name = "precio", nullable = false, scale = 2)
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta_dueño", nullable = false, referencedColumnName = "id_punto_venta")
    private PuntoVenta pointOfSaleOwnerProduct;
}