package dev.facturador.branch.application.handlers;

import dev.facturador.branch.application.BranchRepository;
import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.exception.BranchBadSorting;
import dev.facturador.branch.domain.query.PagingBranchesQuery;
import dev.facturador.global.domain.abstractcomponents.query.QueryHandler;
import dev.facturador.global.domain.sharedpayload.Page;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Manejador de la Query {@link PagingBranchesQuery}
 */
@Slf4j
@AllArgsConstructor
@Service
public class PagingBranchesQueryHandler implements QueryHandler<PagedResponse<Branch>, PagingBranchesQuery> {
    @Autowired
    private final BranchRepository repository;

    @Override
    public PagedResponse<Branch> handle(PagingBranchesQuery query) throws Exception {
        log.info("entre a lhandler");
        validatePageNumberAndSize(query.getPage().getIndex(), query.getPage().getSize(), query.getPage().getOrder());
        log.info("pase validaciones");
        var pageable = createPageable(query.getPage());
        log.info("cree pageable");
        log.info("pageable is: {}", pageable);
        var pages = repository.findByTraderOwnerTraderId(query.getTraderId(), pageable);
        log.info("pase el repository");

        if (pages.getNumberOfElements() != 0) {
            log.info("entre al if");
            pages.getContent().forEach(branch -> {
                branch.setPointsOfSale(null);
                branch.setLogo(null);
            });
        }

        List<Branch> content = pages.getNumberOfElements() == 0 ? Collections.emptyList() : pages.getContent();
        log.info("cree la lista");
        return new PagedResponse<Branch>(content, pages.getNumber(), pages.getSize(),
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
