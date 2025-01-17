package dev.facturador.pointofsale.domain.subdomain;

import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "points_of_sale_control")
@NoArgsConstructor
@Getter
@Setter
public final class PointsOfSaleControl {
    @Id
    @Column(name = "id_pos_control")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointsOfSaleControlId;

    @Column(name = "current_count", nullable = false)
    private int currentCount;
    @Column(name = "total_count", nullable = false)
    private int totalCount;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "id_trader", nullable = false, unique = true, updatable = false)
    private Trader trader;

    public PointsOfSaleControl(int currentCount, int totalCount) {
        this.currentCount = currentCount;
        this.totalCount = totalCount;
    }

    public PointsOfSaleControl(int currentCount, int totalCount, Trader trader) {
        this.currentCount = currentCount;
        this.totalCount = totalCount;
        this.trader = trader;
    }

    public static PointsOfSaleControl create(RequiredPosControlData data) {
        var control = new PointsOfSaleControl();
        var flag = data.getFlag();
        if (Boolean.FALSE.equals( flag )) {
            control.setCurrentCount(data.getCurrentCount() - 1);
            control.setTotalCount(data.getTotalCount());
        }
        if (Boolean.TRUE.equals( flag )) {
            control.setCurrentCount(data.getCurrentCount() + 1);
            control.setTotalCount(data.getTotalCount() + 1);
        }
        control.setPointsOfSaleControlId(data.getPosControlId());

        return control;
    }
}
