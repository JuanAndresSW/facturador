package dev.facturador.pointofsale.application.query;

import dev.facturador.branch.domain.exception.BranchBadSorting;
import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import dev.facturador.pointofsale.application.usecase.ListPointOfSaleUseCase;
import dev.facturador.pointofsale.domain.PointOfSale;
import org.springframework.stereotype.Component;

@Component
public class PointOfSaleListQueryHandler implements QueryHandler<PagedResponse<PointOfSale>, PointOfSaleListQuery> {
    private final ListPointOfSaleUseCase useCase;

    public PointOfSaleListQueryHandler(ListPointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public PagedResponse handle(PointOfSaleListQuery query) throws Exception {
        validatePageNumberAndSize
                (query.getPage().getIndex(), query.getPage().getSize(), query.getPage().getOrder());

        var response = useCase.handlePointOfSaleList(query.getBranchID(), query.getPage());

        return response;
    }

    private void validatePageNumberAndSize(int index, int size, String order) throws BranchBadSorting {
        if (index < 0) {
            throw new BranchBadSorting("Page number cannot be less than zero.");
        }

        if (size < 0) {
            throw new BranchBadSorting("Size number cannot be less than zero.");
        }

        if (size > 12) {
            throw new BranchBadSorting("Page size must not be greater than " + 12);
        }
        if (!order.equals("asc") && !order.equals("desc")) {
            throw new BranchBadSorting("Order not found");
        }
    }
}
