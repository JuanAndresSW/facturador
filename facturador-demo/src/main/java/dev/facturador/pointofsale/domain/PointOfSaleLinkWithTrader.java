package dev.facturador.pointofsale.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PointOfSaleLinkWithTrader {
    private String businessName;
    private String vatCategory;
    private String uniqueKey;

    public static PointOfSaleLinkWithTrader starter
            (String businessName, String vatCategory, String uniqueKey) {
        return new PointOfSaleLinkWithTrader(businessName, vatCategory, uniqueKey);
    }
}
