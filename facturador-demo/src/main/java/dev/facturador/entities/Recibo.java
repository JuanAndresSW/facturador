package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "recibo")
@NoArgsConstructor @Getter @Setter @ToString
public final class Recibo {

    @Id
    @Column(name = "id_recibo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceipt;

    @Column(name = "num_recibo", nullable = false)
    private int numReceipt;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate issueDate;

    @Column(name = "tipo", nullable = false, length = 10)
    private String typeReceipt;

    @Column(name = "pagador", nullable = false, length = 40)
    private String payer;

    @Column(name = "domicilio", nullable = false, length = 40)
    private String home;

    @Column(name = "descipcion", nullable = false, length = 20)
    private String description;

    @Column(name = "cantidad", nullable = false)
    private int amountReceipt;

    @Column(name = "flujo", nullable = false, length = 1)
    private String fluxReceipt;

    public Recibo(long idReceipt) {
        this.idReceipt = idReceipt;
    }
}