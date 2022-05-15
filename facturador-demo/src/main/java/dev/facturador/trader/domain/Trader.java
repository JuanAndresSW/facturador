package dev.facturador.trader.domain;

import dev.facturador.shared.domain.sharedpayload.Vat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    private long idTrader;

    @Column(name = "unique_key", nullable = false, length = 15, updatable = false, unique = true)
    private String uniqueKey;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "vat_category", nullable = false,
            columnDefinition = "enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE')")
    private Vat vat;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "passive", nullable = false)
    private int passive;

    @Column(name = "active", nullable = false)
    private int active;

    public Trader(long idTrader) {
        this.idTrader = idTrader;
    }

    public Trader(long idTrader, String uniqueKey, String name) {
        this.idTrader = idTrader;
        this.uniqueKey = uniqueKey;
        this.name = name;
    }

    public Trader(String uniqueKey, String name, int active, int passive) {
        this.uniqueKey = uniqueKey;
        this.name = name;
        this.active = active;
        this.passive = passive;
    }

    public Trader(String uniqueKey, String name) {
        this.uniqueKey = uniqueKey;
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
                "idTrader=" + idTrader +
                ", uniqueKey='" + uniqueKey + '\'' +
                ", vatCategory=" + vat.getNameVat() +
                ", name='" + name + '\'' +
                ", active='" + active + '\'' +
                ", passive='" + passive + '\'' +
                '}';
    }
}
