package dev.facturador.pointofsale.domain.querys;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public final class PointOfSaleGetQuery extends Query<HashMap<String, Object>> {

    private final Long pointOfSaleID;

}