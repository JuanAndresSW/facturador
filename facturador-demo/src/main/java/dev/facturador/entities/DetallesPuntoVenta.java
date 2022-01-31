package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalles_punto_venta")
@NoArgsConstructor @Getter @Setter @ToString
public final class DetallesPuntoVenta implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta", nullable = false)
    private PuntoVenta pointOfSaleDetails;

    @Column(name = "logo", nullable = false)
    private Long logo;

    @Column(name = "preferencia_color", nullable = false, length = 6)
    private String colorPreference;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetallesPuntoVenta that = (DetallesPuntoVenta) o;

        if (!pointOfSaleDetails.equals(that.pointOfSaleDetails)) return false;
        if (!logo.equals(that.logo)) return false;
        return colorPreference.equals(that.colorPreference);
    }

    @Override
    public int hashCode() {
        int result = pointOfSaleDetails.hashCode();
        result = 31 * result + logo.hashCode();
        result = 31 * result + colorPreference.hashCode();
        return result;
    }
}