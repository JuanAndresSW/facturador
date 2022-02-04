package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "cheque")
@NoArgsConstructor @Getter @Setter @ToString
public final class Cheque {

    @Id
    @Column(name = "id_cheque")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCheck;

    @Column(name = "num_cheque", nullable = false)
    private int numCheck;

    @Column(nullable = false, length = 1)
    private String serie;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate issueDate;

    @Column(name = "cantidad", nullable = false, scale = 2)
    private double amountCheck;

    @Column(name = "banco", nullable = false, length = 20)
    private String bankName;

    @Column(name = "cruzado", nullable = false)
    private boolean crossed;


}