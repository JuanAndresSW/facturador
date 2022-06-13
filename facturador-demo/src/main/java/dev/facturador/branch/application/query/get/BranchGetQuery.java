package dev.facturador.branch.application.query.get;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchID;
import dev.facturador.shared.application.querys.Query;

public class BranchGetQuery extends Query<Branch> {
    private BranchID branchID;

    public BranchGetQuery(BranchID branchID) {
        this.branchID = branchID;
    }

    public BranchID getBranchID() {
        return this.branchID;
    }

    public static class Builder {
        private BranchID branchID;

        public static BranchGetQuery.Builder getInstance() {
            return new BranchGetQuery.Builder();
        }

        public BranchGetQuery.Builder branchID(BranchID branchID) {
            this.branchID = branchID;
            return this;
        }

        public BranchGetQuery build() {
            return new BranchGetQuery(branchID);
        }
    }
}
