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
    private Integer idTransaccion;

    @Column(name = "num_transaccion", nullable = false)
    private int numTransaccion;

    @Column(nullable = false)
    private String tipo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta", nullable = false)
    private PuntoVenta puntoVenta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_orden_compra")
    private OrdenCompra ordenCompra;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_remito")
    private Remito remito;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_factura")
    private Factura factura;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pagare")
    private Pagare pagare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cheque")
    private Cheque cheque;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recibo_x")
    private ReciboX reciboX;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recibo")
    private Recibo recibo;

}