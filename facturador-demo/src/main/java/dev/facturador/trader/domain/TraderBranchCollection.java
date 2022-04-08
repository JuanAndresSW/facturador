package dev.facturador.trader.domain;

import dev.facturador.branch.domain.Branch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class TraderBranchCollection {
    private List<TraderBranchInfo> branchInfoList;

    public static TraderBranchCollection starter(List<Branch> branchList){
        var branchInfoList = new TraderBranchCollection();
        if(!branchList.isEmpty()){
            branchList.stream().forEach(x -> branchInfoList.getBranchInfoList().add(
                    TraderBranchInfo.starter(
                            x.getPhoto().getPhoto(),
                            x.getBranchId(),
                            x.getName(),
                            x.getStreet(),
                            x.getNumberAddress())));
            return branchInfoList;
        }

        return null;
    }
}
