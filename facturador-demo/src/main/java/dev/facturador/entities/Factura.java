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
    @Column(name = "id_factura", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInvoice;

    @Column(name = "num_factura", nullable = false)
    private int numInvoice;

    @Column(nullable = false, length = 1)
    private String flux;

    @Column(nullable = false, length = 1)
    private String type;

    @Column(name = "fecha_emision", nullable = false)
    private Date issueDate;

    @Column(name = "forma_pago", nullable = false)
    private String wayToPay;

    @Column(nullable = false, length = 2)
    private String tax;

    @Column(nullable = false, length = 1)
    private String vat;

    private String observations;

    public Factura(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }
}