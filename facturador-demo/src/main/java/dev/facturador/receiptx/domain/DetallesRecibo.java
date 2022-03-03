package dev.facturador.receiptx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "detalles_recibo")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class DetallesRecibo {

    @Id
    @Column(name = "id_detalles_recibo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceiptDetails;

    @Column(name = "num", nullable = false)
    private int num;

    @Column(name = "fecha_deposito", nullable = false)
    private LocalDate depositDate;

    @Column(name = "importe", nullable = false, scale = 2)
    private double amountReceipt;

    @Column(name = "tipo_valores", nullable = false, length = 255)
    private String typeValue;

    /*
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "id_recibo_x", nullable = false)
     */
    @Column(name = "id_recibo_x", nullable = false)
    private int receiptX;


}