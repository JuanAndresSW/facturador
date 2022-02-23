package dev.facturador.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "orden_compra")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class OrdenCompra {

    @Id
    @Column(name = "id_orden_compra")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPurchaseOrder;

    @Column(name = "num_orden_compra", nullable = false)
    private int purchaseOrderNum;

    @Column(name = "id_punto_venta_emisor", nullable = false)
    private int issuingPointOfSale;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime purchaseOrderIssueDate;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDate deadline;

    @Column(name = "lugar_entrega", nullable = false, length = 20)
    private String dispatchPlace;

    @Column(name = "nombre_transportista", nullable = false, length = 25)
    private String carrierName;

    @Column(name = "condiciones", nullable = false, length = 20)
    private String terms;
}