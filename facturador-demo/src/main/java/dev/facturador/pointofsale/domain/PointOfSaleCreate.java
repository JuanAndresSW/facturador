package dev.facturador.pointofsale.domain;

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
    private int actualNumber;

    public static PointOfSaleCreate create(Long IDBranch, Long IDTrader, int actualNumber) {
        return new PointOfSaleCreate(IDBranch, IDTrader, actualNumber);
    }
}
