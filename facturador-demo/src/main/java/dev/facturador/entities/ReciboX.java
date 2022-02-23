package dev.facturador.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "recibo_x")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class ReciboX {

    @Id
    @Column(name = "id_recibo_x")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceiptX;

    @Column(name = "num_recibo_x", nullable = false)
    private int receiptXNum;

    @Column(name = "id_punto_venta_emisor", nullable = false)
    private int issuingPointOfSale;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime receiptXIssueDate;

    @Column(name = "efectivo", nullable = false, scale = 2)
    private double payCash;

    @Column(name = "documentos", nullable = false, scale = 2)
    private double payDocuments;

    @Column(name = "cheque", nullable = false, scale = 2)
    private double payCheck;

    @Column(name = "horario", nullable = false)
    private LocalTime horary;

    @Column(name = "pagador", nullable = false, length = 40)
    private String payerReceiptX;

    @Column(name = "domicilio_pago", nullable = false, length = 20)
    private String paymentAddress;
}