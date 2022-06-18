package dev.facturador.branch.application.query.tolist;

import dev.facturador.branch.application.usecase.ListBranchUseCase;
import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.exception.BranchBadSorting;
import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import org.springframework.stereotype.Component;

@Component
public class BranchListQueryHandler implements QueryHandler<PagedResponse<Branch>, BranchListQuery> {
    private final ListBranchUseCase useCase;

    public BranchListQueryHandler(ListBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public PagedResponse handle(BranchListQuery query) throws Exception {
        validatePageNumberAndSize(query.getPage().getIndex(), query.getPage().getSize(), query.getPage().getOrder());

        var response = useCase.handleBranchList(query.getTraderId(), query.getPage());

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
