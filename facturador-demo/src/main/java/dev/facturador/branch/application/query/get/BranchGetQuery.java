package dev.facturador.branch.application.query.get;

import dev.facturador.branch.domain.Branch;
import dev.facturador.global.application.querys.Query;
import lombok.Getter;

@Getter
public class BranchGetQuery extends Query<Branch> {
    private final Long branchId;

    public BranchGetQuery(Long branchId) {
        this.branchId = branchId;
    }


    public static class Builder {
        private Long branchId;

        public static BranchGetQuery.Builder getInstance() {
            return new BranchGetQuery.Builder();
        }

        public BranchGetQuery.Builder branchId(Long branchId) {
            this.branchId = branchId;
            return this;
        }

        public BranchGetQuery build() {
            return new BranchGetQuery(branchId);
        }
    }
}
