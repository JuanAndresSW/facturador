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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_transaccion", nullable = false)
    private Transaccion transaction;

    @Column(name = "cantidad", nullable = false)
    private int amountProducts;

    @Column(name = "nombre", nullable = false, length = 50)
    private String name;

    @Column(name = "precio", nullable = false)
    private double price;
}