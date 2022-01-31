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
    private Integer idRemito;

    @Column(name = "num_remito", nullable = false)
    private int numRemito;

    @Column(nullable = false, length = 1)
    private String flujo;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    private String observaciones;
}