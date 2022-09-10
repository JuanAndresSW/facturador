package dev.facturador.pointofsale.domain.querys;

import dev.facturador.global.domain.abstractcomponents.querys.Query;
import dev.facturador.global.domain.sharedpayload.Page;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import dev.facturador.pointofsale.domain.PointOfSale;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Query para el listado de punto de venta
 */
@AllArgsConstructor
@Getter
public class PointOfSaleListQuery extends Query<PagedResponse<PointOfSale>> {
    private final Long branchId;
    private final Page page;

    /**
     * Builder de la Query
     */
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
