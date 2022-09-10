package dev.facturador.branch.domain.query;

import dev.facturador.branch.domain.Branch;
import dev.facturador.global.domain.abstractcomponents.query.Query;
import lombok.Builder;
import lombok.Data;

/**
 * Query para recuperar sucursal
 */
@Data
@Builder
public class BranchGetQuery extends Query<Branch> {
    private final Long branchId;

}
