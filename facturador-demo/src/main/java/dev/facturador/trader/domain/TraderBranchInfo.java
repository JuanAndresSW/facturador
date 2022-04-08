package dev.facturador.trader.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public final class TraderBranchInfo {
    private String photo;
    private Long IDBranch;
    private String name;
    private String street;
    private String numberAddress;

    public static TraderBranchInfo starter(String photo, Long IDBranch, String name, String street, String numberAddress){
        return new TraderBranchInfo(photo, IDBranch, name, street, numberAddress);
    }
}
