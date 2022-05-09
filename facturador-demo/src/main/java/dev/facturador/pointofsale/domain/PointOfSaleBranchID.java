package dev.facturador.pointofsale.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public final class PointOfSaleBranchID {
    private Long branchID;

    public static PointOfSaleBranchID starter(Long branchID){
        return new PointOfSaleBranchID(branchID);
    }
}
