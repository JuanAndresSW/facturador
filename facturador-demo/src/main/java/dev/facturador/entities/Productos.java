package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "productos")
@NoArgsConstructor @Getter @Setter @ToString
public final class Productos {

    @Id
    @Column(name = "id_productos", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_transaccion", nullable = false)
    private Transaccion transaction;

    @Column(nullable = false)
    private int amountProducts;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;
}