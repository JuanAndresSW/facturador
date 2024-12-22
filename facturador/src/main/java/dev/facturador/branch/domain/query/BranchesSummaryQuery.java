package dev.facturador.branch.domain.query;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Query para recuperar una version simplificada de la sucursal
 */
@Data
@Builder
public class BranchesSummaryQuery extends Query<LinkedList<HashMap<String, Object>>> {
    private final Long traderId;
}
