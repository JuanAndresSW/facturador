package dev.facturador.pointofsale.domain;

import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("ALL")
@Entity
@Table(name = "point_of_sale")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class PointOfSale implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Id
    @Column(name = "id_point_of_sale")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPointOfSale;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trader", nullable = false)
    private Trader traderOwner;

}