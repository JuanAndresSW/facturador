package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "transaccion")
@NoArgsConstructor @Getter @Setter @ToString
public final class Transaccion {

    @Id
    @Column(name = "id_transaccion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTransaction;

    @Column(name = "num_transaccion", nullable = false)
    private int numTransaction;

    @Column(name = "tipo", nullable = false, length = 20)
    private String typeTransaction;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta", nullable = false)
    private PuntoVenta pointOfSale;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_orden_compra")
    private OrdenCompra purchaseOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_remito")
    private Remito remito;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_factura")
    private Factura invoice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pagare")
    private Pagare pagare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cheque")
    private Cheque check;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recibo_x")
    private ReciboX receiptX;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recibo")
    private Recibo receipt;
}