package dev.facturador.pointofsale.domain.subdomain;

import dev.facturador.pointofsale.domain.PointOfSale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "details_point_of_sale")
@Setter
@Getter
@NoArgsConstructor
@ToString
public final class DetailsPointOfSale implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_point_of_sale", nullable = false)
    private PointOfSale pointOfSaleDetails;

    @Column(name = "preference_color", nullable = false, length = 6)
    private String colorPreference;

    @Column(name = "logo", nullable = false)
    @Lob
    private String logo;
}