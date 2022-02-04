package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "recibo_x")
@NoArgsConstructor @Getter @Setter @ToString
public final class ReciboX {

    @Id
    @Column(name = "id_recibo_x")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceiptX;

    @Column(name = "num_recibo_x", nullable = false)
    private int numFactura;

    @Column(name = "flujo", nullable = false, length = 1)
    private String fluxReceiptX;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate issueDate;

    @Column(name = "pagador", nullable = false, length = 40)
    private String payerReceiptX;

    @Column(name = "horario", nullable = false)
    private LocalDateTime horary;

    @Column(name = "cheque", nullable = false, scale = 2)
    private double check;

    @Column(name = "documentos", nullable = false, scale = 2)
    private double documents;

    @Column(name = "efectivo", nullable = false, scale = 2)
    private double cash;

    @Column(name = "domicilio_pago", nullable = false, length = 20)
    private String paymentAddress;
}