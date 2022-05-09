package dev.facturador.pointofsale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.branch.domain.Branch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "point_of_sale")
@NoArgsConstructor
@Getter
@Setter
public class PointOfSale implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_point_of_sale")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointOfSaleId;

    @Column(name = "point_of_sale_number", nullable = false)
    private int pointOfSaleNumber;
    @Column(name = "floor", nullable = false, length = 5)
    private String floor;
    @Column(name = "unit", nullable = false, length = 5)
    private String unit;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_branch", nullable = false, updatable = false)
    private Branch branchOwner;

    public PointOfSale(String floor, String unit){
        this.floor = floor;
        this.unit = unit;
    }

    public static PointOfSale create(PointOfSaleCreate values){
        var pointOfSale = new PointOfSale(values.getFloor(), values.getUnit());
        if(values.getActualNumber() == 0){
            pointOfSale.setPointOfSaleNumber(1);
        }
        if(values.getActualNumber() != 0){
            pointOfSale.setPointOfSaleNumber(values.getActualNumber()+1);
        }

        pointOfSale.setBranchOwner(new Branch(values.getIDBranch()));
        return pointOfSale;
    }
}
