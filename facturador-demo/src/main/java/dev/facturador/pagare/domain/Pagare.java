package dev.facturador.pagare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "pagare")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Pagare {

    @Id
    @Column(name = "id_pagare")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPagare;

    @Column(name = "num_pagare", nullable = false)
    private int pagareNum;

    @Column(name = "id_punto_venta_emisor", nullable = false)
    private int issuingPointOfSale;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime pagareIssueDate;

    @Column(name = "cantidad", nullable = false, scale = 2)
    private double amountPagare;

    @Column(name = "beneficiario", nullable = false, length = 255)
    private String beneficiary;

    @Column(name = "contacto", nullable = false, length = 255)
    private String contact;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "protesto", nullable = false)
    private boolean protest;

    @Column(name = "sellado", nullable = false)
    private boolean sellado;
}