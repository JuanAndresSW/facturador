package dev.facturador.entities;

import lombok.*;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Table(name="comerciante")
@NoArgsConstructor @Getter @Setter
public final class Comerciante {

    @Id
    @Column(name="id_comerciante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTrader;

    @Column(name = "clave_unica", nullable = false, length = 15, updatable = false)
    private String uniqueKey;

    @Column(name = "iva", nullable = false, length = 25)
    private String vat;

    @Column(name = "ingresos_brutos", nullable = false, length = 15)
    private String grossIncome;

    @Column(name = "nombre", nullable = false , length = 30)
    private String name;

    @OneToMany(mappedBy = "pointOfSaleOwner", cascade = CascadeType.ALL)
    private List<PuntoVenta> pointOfSaleOutlets;

    public Comerciante(String uniqueKey, String vat, String grossIncome, String name) {
        this.uniqueKey = uniqueKey;
        this.vat = vat;
        this.grossIncome = grossIncome;
        this.name = name;
    }

    public Comerciante(String uniqueKey, String vat, String name) {
        this.uniqueKey = uniqueKey;
        this.vat = vat;
        this.name = name;
    }

    private void addPointOfSale(PuntoVenta element){
        if(pointOfSaleOutlets == null) pointOfSaleOutlets = new LinkedList<>();
        this.pointOfSaleOutlets.add(element);
    }

    @Override
    public String toString() {
        return "Comerciante{" +
                "idTrader=" + idTrader +
                ", uniqueKey='" + uniqueKey + '\'' +
                ", vat='" + vat + '\'' +
                ", grossIncome='" + grossIncome + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
