package dev.facturador.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "recibo")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Recibo {

    @Id
    @Column(name = "id_recibo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceipt;

    @Column(name = "num_recibo", nullable = false)
    private int receiptNum;

    @Column(name = "id_punto_venta_emisor", nullable = false)
    private int issuingPointOfSale;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime receiptIssueDate;

    @Column(name = "cantidad", nullable = false)
    private int amountReceipt;

    @Column(name = "domicilio", nullable = false, length = 40)
    private String home;

    @Column(name = "pagador", nullable = false, length = 40)
    private String payer;

    @Column(name = "tipo", nullable = false, length = 10)
    private String typeReceipt;


}