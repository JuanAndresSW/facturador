package dev.facturador.pointofsale.application.handlers;

import dev.facturador.branch.domain.exception.BranchBadSorting;
import dev.facturador.global.domain.abstractcomponents.querys.QueryHandler;
import dev.facturador.global.domain.sharedpayload.Page;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import dev.facturador.pointofsale.application.PointOfSaleRepository;
import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.querys.PointOfSaleListQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Maneja la Query de
 */
@AllArgsConstructor
@Service
public class PointOfSaleListQueryHandler implements QueryHandler<PagedResponse<PointOfSale>, PointOfSaleListQuery> {
    @Autowired
    private final PointOfSaleRepository repository;

    @Override
    public PagedResponse<PointOfSale> handle(PointOfSaleListQuery query) throws Exception {
        //Validacion
        validatePageNumberAndSize(query.getPage().getIndex(), query.getPage().getSize(), query.getPage().getOrder());

        Pageable pageable = createPageable(query.getPage());
        var pages = repository.findByBranchOwnerBranchId(query.getBranchId(), pageable);

        List<PointOfSale> content = pages.getNumberOfElements() == 0 ? Collections.emptyList() : pages.getContent();
        return new PagedResponse(content, pages.getNumber(), pages.getSize(),
                pages.getTotalElements(), pages.getTotalPages(), pages.isLast());
    }

    private Pageable createPageable(Page page) {
        if (page.getOrder().equals("asc")) {
            return PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.ASC, page.getSort());
        }
        return PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.DESC, page.getSort());
    }

    /**
     * Comprueba que lo datos de paginacion tengan sentido
     *
     * @param index Numero de pagina
     * @param size  Tamaño de pagina
     * @param order Orden puede ser ascendente o descendiente
     */
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
