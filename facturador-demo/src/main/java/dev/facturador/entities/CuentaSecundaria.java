package dev.facturador.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "cuenta_secundaria")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class CuentaSecundaria {
    @Id
    @Column(name = "id_cuenta_secundaria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSecondaryAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta", nullable = false)
    private PuntoVenta legacyPointOfSale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuarios userSecondaryAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cuenta_principal", nullable = false)
    private CuentaPrincipal secondaryAccountOwner;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime createDate;
}