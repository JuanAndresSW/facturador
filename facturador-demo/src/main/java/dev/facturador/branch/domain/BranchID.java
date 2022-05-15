package dev.facturador.branch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor @Getter @Setter
public class BranchID {
    @NotNull
    private long BranchID;

    public static BranchID valueof(long BranchID){
        return new BranchID(BranchID);
    }
}
