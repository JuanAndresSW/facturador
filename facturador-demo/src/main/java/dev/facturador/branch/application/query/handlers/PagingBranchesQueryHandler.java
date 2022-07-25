package dev.facturador.branch.application.query.handlers;

import dev.facturador.branch.application.query.PagingBranchesQuery;
import dev.facturador.branch.application.usecase.PagingBranchesUseCase;
import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.exception.BranchBadSorting;
import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.global.application.sharedpayload.PagedResponse;
import org.springframework.stereotype.Component;

/**Manejador de la Query {@link PagingBranchesQuery}*/
@Component
public class PagingBranchesQueryHandler implements QueryHandler<PagedResponse<Branch>, PagingBranchesQuery> {
    private final PagingBranchesUseCase useCase;

    public PagingBranchesQueryHandler(PagingBranchesUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public PagedResponse<Branch> handleGetBranch(PagingBranchesQuery query) throws Exception {
        //Validacion
        validatePageNumberAndSize(query.getPage().getIndex(), query.getPage().getSize(), query.getPage().getOrder());
        //Sede la paginacion al caso de uso
        var response = useCase.handleBranchList(query.getTraderId(), query.getPage());

        return response;
    }
    /**
     * Comprueba que lo datos de paginacion tengan sentido
     * @param index Numero de pagina
     * @param size Tamaño de pagina
     * @param order Orden puede ser ascendente o descendiente
     * */
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
