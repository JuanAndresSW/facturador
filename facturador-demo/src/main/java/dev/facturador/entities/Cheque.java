package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "cheque")
@NoArgsConstructor @Getter @Setter @ToString
public final class Cheque {

    @Id
    @Column(name = "id_cheque", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCheque;

    @Column(name = "num_cheque", nullable = false)
    private int numCheque;

    @Column(nullable = false, length = 1)
    private String serie;

    @Column(name = "fecha_emision", nullable = false)
    private Date issueDate;

    @Column(name = "cantidad", nullable = false, scale = 2)
    private double amount;

    @Column(nullable = false)
    private String bank;

    @Column(name = "cruzado", nullable = false)
    private boolean crossed;


}