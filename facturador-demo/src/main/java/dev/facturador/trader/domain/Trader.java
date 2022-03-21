package dev.facturador.trader.domain;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.shared.domain.Vat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("ALL")
@Entity
@Table(name = "trader")
@NoArgsConstructor
@Getter
@Setter
public final class Trader implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Id
    @Column(name = "id_trader")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTrader;

    @Column(name = "unique_key", nullable = false, length = 15, updatable = false, unique = true)
    private String uniqueKey;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "vat", nullable = false,
            columnDefinition = "enum('RESPONSABLE_INSCRIPTO','MONOTRIBUTISTA','SUJETO_EXENTO')")
    private Vat vat;

    @Column(name = "gross_income", nullable = false, length = 15)
    private String grossIncome;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "passive", nullable = false)
    private int passive;

    @Column(name = "active", nullable = false)
    private int active;

    @OneToMany(mappedBy = "traderOwner", cascade = CascadeType.ALL)
    private Collection<PointOfSale> pointOfSaleOutlets;

    public Trader(String uniqueKey, String grossIncome, String name, int active, int passive) {
        this.uniqueKey = uniqueKey;
        this.grossIncome = grossIncome;
        this.name = name;
        this.active = active;
        this.passive = passive;
    }

    public Trader(String uniqueKey, String grossIncome, String name) {
        this.uniqueKey = uniqueKey;
        this.grossIncome = grossIncome;
        this.name = name;
    }

    public static Vat defineVat(String vat) {
        if (vat.contains("Responsable")) {
            return Vat.RESPONSABLE_INSCRIPTO;
        }
        if (vat.contains("Monotributista")) {
            return Vat.MONOTRIBUTISTA;
        }
        if (vat.contains("Sujeto")) {
            return Vat.SUJETO_EXENTO;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "idTrader=" + idTrader +
                ", uniqueKey='" + uniqueKey + '\'' +
                ", vatCategory=" + vat.getNameVat() +
                ", grossIncome='" + grossIncome + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
