package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "cuenta_secundaria")
@NoArgsConstructor @Getter @Setter @ToString
public final class CuentaSecundaria {

    @Id
    @Column(name = "id_cuenta_secundaria", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSecondaryAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cuenta_principal", nullable = false)
    private CuentaPrincipal mainAccount;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detalles_cuenta", nullable = false, unique = true, updatable = false)
    private DetallesCuenta secondaryAccountDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta", nullable = false)
    private PuntoVenta legacyPointOfSale;
}