package dev.facturador.trader.domain;

import dev.facturador.entities.enums.Vat;
import dev.facturador.pointofsale.domain.PuntoVenta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@SuppressWarnings("ALL")
@Entity
@Table(name = "comerciante")
@NoArgsConstructor
@Getter
@Setter
public final class Comerciante {

    @Id
    @Column(name = "id_comerciante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTrader;

    @Column(name = "clave_unica", nullable = false, length = 15, updatable = false, unique = true)
    private String uniqueKey;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "iva", nullable = false,
            columnDefinition = "ENUM('Responsable Inscripto','Monotributista','Sujeto Exento')")
    private Vat vat;

    @Column(name = "ingresos_brutos", nullable = false, length = 15)
    private String grossIncome;

    @Column(name = "nombre", nullable = false, length = 20)
    private String name;

    @Column(name = "pasivo", nullable = false)
    private int passive;

    @Column(name = "activo", nullable = false)
    private int active;

    @OneToMany(mappedBy = "traderOwner", cascade = CascadeType.ALL)
    private Collection<PuntoVenta> pointOfSaleOutlets;

    public Comerciante(String uniqueKey, String grossIncome, String name, int active, int passive) {
        this.uniqueKey = uniqueKey;
        this.vat = vat;
        this.grossIncome = grossIncome;
        this.name = name;
        this.active = active;
        this.passive = passive;
    }

    public Comerciante(String uniqueKey, String grossIncome, String name) {
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
        return "Comerciante{" +
                "idTrader=" + idTrader +
                ", uniqueKey='" + uniqueKey + '\'' +
                ", vat=" + vat.getNameVat() +
                ", grossIncome='" + grossIncome + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
