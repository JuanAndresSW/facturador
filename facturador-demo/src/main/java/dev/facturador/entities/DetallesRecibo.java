package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "detalles_recibo")
@NoArgsConstructor @Getter @Setter @ToString
public final class DetallesRecibo {

    @Id
    @Column(name = "id_detalles_recibo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceiptDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recibo_x", nullable = false)
    private ReciboX receiptX;

    @Column(nullable = false)
    private int num;

    @Column(name = "tipo_valores", nullable = false)
    private String typeValue;

    @Column(name = "fecha_deposito", nullable = false)
    private LocalDate depositDate;

    @Column(name = "importe", nullable = false)
    private double amountReceipt;
}