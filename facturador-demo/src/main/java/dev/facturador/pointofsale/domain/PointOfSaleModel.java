package dev.facturador.pointofsale.domain;

import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


public record PointOfSaleModel(Long IDBranch, Long IDTrader, PointsOfSaleControl posControl) {

    public static PointOfSaleModel valueOf(Long IDBranch, Long IDTrader, PointsOfSaleControl posControl) {
        return new PointOfSaleModel(IDBranch, IDTrader, posControl);
    }
}
