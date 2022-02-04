package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalles_punto_venta")
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode @ToString
public final class DetallesPuntoVenta implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta")
    private PuntoVenta pointOfSaleDetails;

    @Column(name = "logo", nullable = false)
    private Long logo;

    @Column(name = "preferencia_color", nullable = false, length = 6)
    private String colorPreference;

}