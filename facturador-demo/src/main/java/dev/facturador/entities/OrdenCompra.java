package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "orden_compra")
@NoArgsConstructor @Getter @Setter @ToString
public final class OrdenCompra {

    @Id
    @Column(name = "id_orden_compra", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPurchaseOrder;

    @Column(name = "num_orden_compra", nullable = false)
    private int numPurchaseOrder;

    @Column(nullable = false, length = 1)
    private String flux;

    @Column(name = "fecha_emision", nullable = false)
    private Date issueDate;

    @Column(name = "fecha_limite", nullable = false)
    private Date deadline;

    @Column(nullable = false)
    private String terms;

    @Column(name = "lugar_entrega", nullable = false)
    private String dispatchPlace;

    @Column(name = "nombre_transportista", nullable = false)
    private String carrier;

    private String observations;

    public OrdenCompra(Integer idPurchaseOrder) {
        this.idPurchaseOrder = idPurchaseOrder;
    }
}