package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "pagare")
@NoArgsConstructor @Getter @Setter @ToString
public final class Pagare {

    @Id
    @Column(name = "id_pagare")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPagare;

    @Column(name = "num_pagare", nullable = false)
    private int numPagare;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate issueDate;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "beneficiario", nullable = false)
    private String beneficiary;

    @Column(name = "cantidad", nullable = false)
    private double amountPagare;

    @Column(name = "protesto", nullable = false)
    private boolean protest;

    @Column(name = "sellado", nullable = false)
    private boolean sellado;

    @Column(name = "contacto", nullable = false)
    private String contact;

    @Column(name = "descripcion", nullable = false)
    private String description;
}