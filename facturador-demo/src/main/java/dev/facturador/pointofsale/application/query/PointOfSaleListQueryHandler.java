package dev.facturador.pointofsale.application.query;

import dev.facturador.branch.domain.exception.BranchBadSorting;
import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.global.application.sharedpayload.PagedResponse;
import dev.facturador.pointofsale.application.usecase.ListPointOfSaleUseCase;
import dev.facturador.pointofsale.domain.PointOfSale;
import org.springframework.stereotype.Component;

/**Maneja la Query de {@link PointOfSaleListQuery}*/
@Component
public class PointOfSaleListQueryHandler implements QueryHandler<PagedResponse<PointOfSale>, PointOfSaleListQuery> {
    private final ListPointOfSaleUseCase useCase;

    public PointOfSaleListQueryHandler(ListPointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public PagedResponse handleGetBranch(PointOfSaleListQuery query) throws Exception {
        //Validacion
        validatePageNumberAndSize
                (query.getPage().getIndex(), query.getPage().getSize(), query.getPage().getOrder());
        //Sede la paginacion al caso de uso
        var response = useCase.handlePointOfSaleList(query.getBranchId(), query.getPage());

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
