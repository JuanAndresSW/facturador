package dev.facturador.pointofsale.domain.querys;

import dev.facturador.global.domain.abstractcomponents.querys.Query;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;

@Data
@Builder
public final class PointOfSaleGetQuery extends Query<HashMap<String, Object>> {

    private final Long pointOfSaleID;

}