package dev.facturador.pointofsale.application.query;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleBranchID;
import dev.facturador.shared.application.querys.Query;
import dev.facturador.shared.domain.sharedpayload.Page;
import dev.facturador.shared.domain.sharedpayload.PagedResponse;
import lombok.Getter;

@Getter
public class PointOfSaleListQuery extends Query<PagedResponse<PointOfSale>> {
    private PointOfSaleBranchID branchID;
    private Page page;

    public PointOfSaleListQuery(PointOfSaleBranchID branchID, Page page) {
        this.branchID = branchID;
        this.page = page;
    }

    public static class Builder {
        private PointOfSaleBranchID branchID;
        private Page page;

        public static PointOfSaleListQuery.Builder getInstance() {
            return new PointOfSaleListQuery.Builder();
        }

        public PointOfSaleListQuery.Builder branchID(PointOfSaleBranchID branchID) {
            this.branchID = branchID;
            return this;
        }

        public PointOfSaleListQuery.Builder page(Page page) {
            this.page = page;
            return this;
        }

        public PointOfSaleListQuery build() {
            return new PointOfSaleListQuery(branchID, page);
        }
    }
}
