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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_branch", nullable = false, updatable = false, referencedColumnName = "id_branch")
    private Branch branchOwner;

    public PointOfSale(long pointOfSaleId) {
        super();
        this.pointOfSaleId = pointOfSaleId;
    }

    public static PointOfSale create(PointOfSaleCreate values) {
        var pointOfSale = new PointOfSale();
        pointOfSale.setPointOfSaleNumber(values.getPosControl().getTotalCount() + 1);
        pointOfSale.setBranchOwner(new Branch(values.getIDBranch()));
        return pointOfSale;
    }

    public static PointOfSale create(Long id) {
        var pointOfSale = new PointOfSale();
        pointOfSale.setPointOfSaleId(id);

        return pointOfSale;
    }
}
