package dev.facturador.branch.domain.query;

import dev.facturador.branch.domain.Branch;
import dev.facturador.global.domain.abstractcomponents.querys.Query;
import dev.facturador.global.domain.sharedpayload.Page;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import lombok.Builder;
import lombok.Data;

/**
 * Query para recuperar el lsitado de sucursales devuelve un {@link PagedResponse}
 */
@Data
@Builder
public class PagingBranchesQuery extends Query<PagedResponse<Branch>> {
    private final Long traderId;
    private final Page page;
}
