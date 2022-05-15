package dev.facturador.pointofsale.application.query;

import dev.facturador.branch.domain.exception.BranchBadSorting;
import dev.facturador.pointofsale.application.usecase.ListPointOfSaleUseCase;
import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.shared.application.querybus.QueryHandler;
import dev.facturador.shared.domain.sharedpayload.PagedResponse;
import org.springframework.stereotype.Component;

@Component
public class PointOfSaleListQueryHandler implements QueryHandler<PagedResponse<PointOfSale>, PointOfSaleListQuery> {
    private ListPointOfSaleUseCase useCase;

    public PointOfSaleListQueryHandler(ListPointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public PagedResponse handle(PointOfSaleListQuery query) throws Exception {
        validatePageNumberAndSize
                (query.getPage().getIndex(), query.getPage().getSize(), query.getPage().getOrder());

        var response = useCase.handle(query.getBranchID(), query.getPage());

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
        if(!order.equals("asc") && !order.equals("desc")){
            throw new BranchBadSorting("Order not found");
        }
    }
}
