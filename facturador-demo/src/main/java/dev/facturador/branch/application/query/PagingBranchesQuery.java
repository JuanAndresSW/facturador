package dev.facturador.branch.application.query;

import dev.facturador.branch.domain.Branch;
import dev.facturador.global.application.querys.Query;
import dev.facturador.global.application.sharedpayload.Page;
import dev.facturador.global.application.sharedpayload.PagedResponse;
import lombok.Getter;

/**Query para recuperar el lsitado de sucursales devuelve un {@link PagedResponse}*/
@Getter
public class PagingBranchesQuery extends Query<PagedResponse<Branch>> {
    private final Long traderId;
    private final Page page;

    public PagingBranchesQuery(Long traderId, Page page) {
        this.traderId = traderId;
        this.page = page;
    }
    /**Builder de la Query*/
    public static class Builder {
        private Long traderId;
        private Page page;

        public static PagingBranchesQuery.Builder getInstance() {
            return new PagingBranchesQuery.Builder();
        }

        public PagingBranchesQuery.Builder traderID(Long traderId) {
            this.traderId = traderId;
            return this;
        }

        public PagingBranchesQuery.Builder page(Page page) {
            this.page = page;
            return this;
        }

        public PagingBranchesQuery build() {
            return new PagingBranchesQuery(traderId, page);
        }
    }
}
