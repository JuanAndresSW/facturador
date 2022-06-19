package dev.facturador.pointofsale.domain.subdomain;

import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "control_of_pos")
@NoArgsConstructor
@Getter
@Setter
public final class ControlOfPointOfSale {
    @Id
    @Column(name = "id_control_pos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long controlOfPosId;

    @Column(name = "current_count", nullable = false)
    private int currentCount;
    @Column(name = "total_count", nullable = false)
    private int totalCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trader", nullable = false, updatable = false, insertable = false)
    private Trader trader;

    public ControlOfPointOfSale(int currentCount, int totalCount) {
        this.currentCount = currentCount;
        this.totalCount = totalCount;
    }

    public ControlOfPointOfSale(int currentCount, int totalCount, Trader trader) {
        this.currentCount = currentCount;
        this.totalCount = totalCount;
        this.trader = trader;
    }

    public static ControlOfPointOfSale create(ControlOfPosData data) {
        var control = new ControlOfPointOfSale();
        if(data.getFlag()){
            control.setCurrentCount(data.getCurrentCount()+1);
            control.setTotalCount(data.getTotalCount()+1);
        }
        if(!data.getFlag()){
            control.setCurrentCount(data.getCurrentCount()-1);
            control.setTotalCount(data.getTotalCount());
        }
        control.setControlOfPosId(data.getPosControlId());

        return control;
    }
}
