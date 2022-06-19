package dev.facturador.pointofsale.domain;

import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public final class PointOfSaleCreate {
    private Long IDBranch;
    private Long IDTrader;
    private PointsOfSaleControl posControl;

    public static PointOfSaleCreate valueOf(Long IDBranch, Long IDTrader, PointsOfSaleControl posControl) {
        return new PointOfSaleCreate(IDBranch, IDTrader, posControl);
    }
}
