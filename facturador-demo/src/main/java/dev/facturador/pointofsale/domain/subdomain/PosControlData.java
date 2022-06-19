package dev.facturador.pointofsale.domain.subdomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public final class PosControlData {
    private Long posControlId;
    private Integer currentCount;
    private Integer totalCount;
    private Boolean flag;

    public static PosControlData starter(Long posControlId, Integer currentCount, Integer totalCount, Boolean flag) {
        return new PosControlData(posControlId, currentCount, totalCount, flag);
    }
}
