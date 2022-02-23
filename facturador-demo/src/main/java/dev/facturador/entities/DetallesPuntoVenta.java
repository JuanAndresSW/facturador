package dev.facturador.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "detalles_punto_venta")
@Setter
@Getter
@NoArgsConstructor
@ToString
public final class DetallesPuntoVenta {
    @Id
    @Column(name = "id_detalles_punto_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long detailsPointOfSaleId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta", nullable = false, unique = true)
    private PuntoVenta pointOfSaleDetails;

    @Column(name = "preferencia_color", nullable = false, length = 6)
    private String colorPreference;

    @Column(name = "logo", nullable = false)
    @Lob
    private String logo;
}