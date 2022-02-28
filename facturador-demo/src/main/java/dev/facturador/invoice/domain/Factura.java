package dev.facturador.invoice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "id_punto_venta_emisor", nullable = false)
    private int pointOfSaleIssuerInvoice;

    @Column(name = "fecha_emision", nullable = false, updatable = false)
    private LocalDateTime invoiceIssueDate;

    @Column(name = "tipo", nullable = false, length = 1)
    private String invoiceType;

    @Column(name = "impuesto", nullable = false, length = 2)
    private String tax;

    @Column(name = "iva", nullable = false, length = 1)
    private String vatDetail;

    @Column(name = "forma_pago", length = 255, nullable = false)
    private String wayToPay;

}