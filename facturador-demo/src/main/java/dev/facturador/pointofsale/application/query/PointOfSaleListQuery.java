package dev.facturador.pointofsale.application.query;

import dev.facturador.global.application.querys.Query;
import dev.facturador.global.domain.sharedpayload.Page;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import dev.facturador.pointofsale.domain.PointOfSale;
import lombok.Getter;

@Getter
public class PointOfSaleListQuery extends Query<PagedResponse<PointOfSale>> {
    private final Long branchId;
    private final Page page;

    public PointOfSaleListQuery(Long branchId, Page page) {
        this.branchId = branchId;
        this.page = page;
    }

    public static class Builder {
        private Long branchId;
        private Page page;

        public static PointOfSaleListQuery.Builder getInstance() {
            return new PointOfSaleListQuery.Builder();
        }

        public PointOfSaleListQuery.Builder branchId(Long branchID) {
            this.branchId = branchID;
            return this;
        }

        public PointOfSaleListQuery.Builder page(Page page) {
            this.page = page;
            return this;
        }

        public PointOfSaleListQuery build() {
            return new PointOfSaleListQuery(branchId, page);
        }
    }
}
