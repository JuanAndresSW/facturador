package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "factura")
@NoArgsConstructor @Getter @Setter @ToString
public final class Factura {

    @Id
    @Column(name = "id_factura")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInvoice;

    @Column(name = "num_factura", nullable = false)
    private int numInvoice;

    @Column(name = "flujo", nullable = false, length = 1)
    private String flux;

    @Column(name = "tipo", nullable = false, length = 1)
    private String invoiceType;

    @Column(name = "fecha_emision", nullable = false)
    private Date issueDate;

    @Column(name = "forma_pago", nullable = false)
    private String wayToPay;

    @Column(name = "impuesto", nullable = false, length = 2)
    private String tax;

    @Column(name = "iva", nullable = false, length = 1)
    private String vat;

    @Column(name = "observaciones", nullable = false, length = 60)
    private String observations;

    public Factura(long idInvoice) {
        this.idInvoice = idInvoice;
    }
}