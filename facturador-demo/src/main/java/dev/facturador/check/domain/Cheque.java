package dev.facturador.check.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "cheque")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Cheque {

    @Id
    @Column(name = "id_cheque")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCheck;

    @Column(name = "num_cheque", nullable = false)
    private int numCheck;

    @Column(name = "id_punto_venta_emisor", nullable = false)
    private int issuingPointOfSale;

    @Column(name = "fecha_emision", nullable = false, updatable = false)
    private LocalDateTime checkIssueDate;

    @Column(nullable = false, length = 1)
    private String serie;

    @Column(name = "cantidad", nullable = false, scale = 2)
    private double amountCheck;

    @Column(name = "banco", nullable = false, length = 20)
    private String bankName;

    @Column(name = "cruzado", nullable = false)
    private boolean crossed;


}