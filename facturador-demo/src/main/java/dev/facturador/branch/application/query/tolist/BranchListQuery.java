package dev.facturador.branch.application.query.tolist;

import dev.facturador.branch.domain.Branch;
import dev.facturador.global.application.querys.Query;
import dev.facturador.global.domain.sharedpayload.Page;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import lombok.Getter;

@Getter
public class BranchListQuery extends Query<PagedResponse<Branch>> {
    private final Long traderId;
    private final Page page;

    public BranchListQuery(Long traderId, Page page) {
        this.traderId = traderId;
        this.page = page;
    }

    public static class Builder {
        private Long traderId;
        private Page page;

        public static BranchListQuery.Builder getInstance() {
            return new BranchListQuery.Builder();
        }

        public BranchListQuery.Builder traderID(Long traderId) {
            this.traderId = traderId;
            return this;
        }

        public BranchListQuery.Builder page(Page page) {
            this.page = page;
            return this;
        }

        public BranchListQuery build() {
            return new BranchListQuery(traderId, page);
        }
    }
}
