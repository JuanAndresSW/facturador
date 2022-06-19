package dev.facturador.pointofsale.domain.subdomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public final class ControlOfPosData {
    private Long posControlId;
    private Integer currentCount;
    private Integer totalCount;
    private Boolean flag;

    public static ControlOfPosData starter(Long posControlId, Integer currentCount, Integer totalCount, Boolean flag){
        return new ControlOfPosData(posControlId, currentCount, totalCount, flag);
    }
}
