package dev.facturador.branch.application.query.tolist;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchTraderId;
import dev.facturador.shared.application.querys.Query;
import dev.facturador.shared.domain.sharedpayload.Page;
import dev.facturador.shared.domain.sharedpayload.PagedResponse;
import lombok.Getter;

@Getter
public class BranchListQuery extends Query<PagedResponse<Branch>> {
    private BranchTraderId branchTraderId;
    private Page page;

    public BranchListQuery(BranchTraderId branchTraderId, Page page) {
        this.branchTraderId = branchTraderId;
        this.page = page;
    }

    public static class Builder {
        private BranchTraderId branchTraderId;
        private Page page;

        public static BranchListQuery.Builder getInstance() {
            return new BranchListQuery.Builder();
        }

        public BranchListQuery.Builder traderID(BranchTraderId branchTraderId) {
            this.branchTraderId = branchTraderId;
            return this;
        }

        public BranchListQuery.Builder page(Page page) {
            this.page = page;
            return this;
        }

        public BranchListQuery build() {
            return new BranchListQuery(branchTraderId, page);
        }
    }
}
