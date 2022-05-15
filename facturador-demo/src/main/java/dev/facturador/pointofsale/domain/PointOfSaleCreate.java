package dev.facturador.pointofsale.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @NoArgsConstructor @AllArgsConstructor @ToString
public final class PointOfSaleCreate {
    private int actualNumber;
    private String floor;
    private String unit;

    private transient Long IDBranch;

    public void addID(long IDBranch){
        this.IDBranch = IDBranch;
    }
}
