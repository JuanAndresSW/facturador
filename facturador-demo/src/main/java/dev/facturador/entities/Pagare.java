package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "pagare")
@NoArgsConstructor @Getter @Setter @ToString
public final class Pagare {

    @Id
    @Column(name = "id_pagare", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagare;

    @Column(name = "num_pagare", nullable = false)
    private int numPagare;

    @Column(name = "fecha_emision", nullable = false)
    private Date issueDate;

    @Column(name = "fecha_vencimiento", nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private String beneficiary;

    @Column(name = "cantidad", nullable = false)
    private double amount;

    @Column(name = "protesto", nullable = false)
    private boolean protest;

    @Column(name = "sellado", nullable = false)
    private boolean sellado;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String description;
}