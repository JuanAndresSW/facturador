package dev.facturador.trader.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.branch.domain.Branch;
import dev.facturador.global.domain.sharedpayload.Vat;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("ALL")
@Entity
@Table(name = "trader")
@NoArgsConstructor
@Getter
@Setter
public final class Trader implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_trader")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long traderId;

    @Column(nullable = false, length = 15, updatable = false, unique = true)
    private String cuit;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "vat_category", nullable = false,
            columnDefinition = "enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE')")
    private Vat vat;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private int passives;

    @Column(nullable = false)
    private int actives;

    @OneToOne(mappedBy = "trader", cascade = CascadeType.ALL)
    private PointsOfSaleControl pointsOfSaleControl;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "traderOwner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Branch> branchesCreated;

    public Trader(long traderId) {
        this.traderId = traderId;
    }

    public Trader(long traderId, String cuit, String name) {
        this.traderId = traderId;
        this.cuit = cuit;
        this.name = name;
    }

    public Trader(String cuit, String name, int actives, int passives) {
        this.cuit = cuit;
        this.name = name;
        this.actives = actives;
        this.passives = passives;
    }

    public Trader(String cuit, String name) {
        this.cuit = cuit;
        this.name = name;
    }

    public static Vat defineVat(String vat) {
        if (vat.contains("Inscripto")) {
            return Vat.REGISTERED_RESPONSIBLE;
        }
        return Vat.MONOTAX_RESPONSIBLE;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "idTrader=" + traderId +
                ", cuit='" + cuit + '\'' +
                ", vatCategory=" + vat.getNameVat() +
                ", name='" + name + '\'' +
                ", actives='" + actives + '\'' +
                ", passive='" + passives + '\'' +
                '}';
    }
}
