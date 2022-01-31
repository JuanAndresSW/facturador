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
    private Integer idTrader;

    @Column(name = "clave_unica", nullable = false, length = 15, updatable = false)
    private String uniqueKey;

    @Column(nullable = false, length = 25)
    private String iva;

    @Column(name = "ingresos_brutos", nullable = false, length = 12)
    private String grossIncome;

    @Column(nullable = false , length = 30)
    private String name;

    @OneToMany(mappedBy = "pointOfSaleOwner", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE) private List<PuntoVenta> pointOfSaleOutlets;

    private void addPointOfSale(PuntoVenta element){
        if(pointOfSaleOutlets == null) pointOfSaleOutlets = new LinkedList<>();
        this.pointOfSaleOutlets.add(element);
    }


    @Override
    public String toString() {
        return "Comerciante{" +
                "idTrader=" + idTrader +
                ", uniqueKey='" + uniqueKey + '\'' +
                ", iva='" + iva + '\'' +
                ", grossIncome='" + grossIncome + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
