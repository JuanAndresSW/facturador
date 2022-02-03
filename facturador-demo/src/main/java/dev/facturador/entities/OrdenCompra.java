package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "orden_compra")
@NoArgsConstructor @Getter @Setter @ToString
public final class OrdenCompra {

    @Id
    @Column(name = "id_orden_compra")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPurchaseOrder;

    @Column(name = "num_orden_compra", nullable = false)
    private int numPurchaseOrder;

    @Column(name = "flujo", nullable = false, length = 1)
    private String flux;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate issueDate;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDate deadline;

    @Column(name = "condiciones", nullable = false, length = 20)
    private String terms;

    @Column(name = "lugar_entrega", nullable = false, length = 20)
    private String dispatchPlace;

    @Column(name = "nombre_transportista", nullable = false, length = 25)
    private String carrierName;

    @Column(name = "observaciones", nullable = false, length = 60)
    private String observations;

    public OrdenCompra(Integer idPurchaseOrder) {
        this.idPurchaseOrder = idPurchaseOrder;
    }
}