package dev.facturador.pagare.domain;

import dev.facturador.docinicio.domain.DocInicio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

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

    @JoinColumns(value = {
            @JoinColumn(name = "id_punto_venta_emisor", referencedColumnName = "id_punto_venta_emisor", nullable = false),
            @JoinColumn(name = "fecha_emision", referencedColumnName = "fecha_creacion", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private DocInicio skeletonDocOfPagare;

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