package dev.facturador.entities;

import dev.facturador.entities.enums.Vat;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@SuppressWarnings("ALL")
@Entity
@Table(name="comerciante")
@NoArgsConstructor @Getter @Setter
public final class Comerciante {

    @Id
    @Column(name="id_comerciante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTrader;

    @Column(name = "clave_unica", nullable = false, length = 15, updatable = false, unique = true)
    private String uniqueKey;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "iva", nullable = false,
            columnDefinition="ENUM('Responsable Inscripto','Monotributista','Sujeto Exento')")
    private Vat vat;

    @Column(name = "ingresos_brutos", nullable = false, length = 15)
    private String grossIncome;

    @Column(name = "nombre", nullable = false , length = 20)
    private String name;

    @OneToMany(mappedBy = "traderOwner", cascade = CascadeType.ALL)
    private Collection<PuntoVenta> pointOfSaleOutlets;

    public Comerciante(String uniqueKey, Vat vat, String grossIncome, String name) {
        this.uniqueKey = uniqueKey;
        this.vat = vat;
        this.grossIncome = grossIncome;
        this.name = name;
    }

    public Comerciante(String uniqueKey, String grossIncome, String name) {
        this.uniqueKey = uniqueKey;
        this.grossIncome = grossIncome;
        this.name = name;
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
