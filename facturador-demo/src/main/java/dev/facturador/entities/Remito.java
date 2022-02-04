package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "remito")
@NoArgsConstructor @Getter @Setter @ToString
public final class Remito {

    @Id
    @Column(name = "id_remito", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRemito;

    @Column(name = "num_remito", nullable = false)
    private int numRemito;

    @Column(name = "flujo", nullable = false, length = 1)
    private String fluxRemito;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate issueDate;

    @Column(name = "observaciones", nullable = false, length = 60)
    private String observations;
}