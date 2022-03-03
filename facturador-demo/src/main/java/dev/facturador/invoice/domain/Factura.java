package dev.facturador.invoice.domain;

import dev.facturador.docinicio.domain.DocInicio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "factura")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Factura {

    @Id
    @Column(name = "id_factura")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;

    @Column(name = "num_factura", nullable = false)
    private int invoiceNum;

    @JoinColumns(value = {
            @JoinColumn(name = "id_punto_venta_emisor", referencedColumnName = "id_punto_venta_emisor", nullable = false),
            @JoinColumn(name = "fecha_emision", referencedColumnName = "fecha_creacion", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private DocInicio skeletonDocOfInvoice;

    @Column(name = "tipo", nullable = false, length = 1)
    private String invoiceType;

    @Column(name = "impuesto", nullable = false, length = 2)
    private String tax;

    @Column(name = "iva", nullable = false, length = 1)
    private String vatDetail;

    @Column(name = "forma_pago", length = 255, nullable = false)
    private String wayToPay;

}