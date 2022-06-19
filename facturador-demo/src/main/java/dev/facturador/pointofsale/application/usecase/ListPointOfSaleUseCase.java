package dev.facturador.pointofsale.application.usecase;

import dev.facturador.global.application.sharedpayload.Page;
import dev.facturador.global.application.sharedpayload.PagedResponse;
import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**Caso de uso para paginar la punto de venta*/
@Service
public final class ListPointOfSaleUseCase {
    @Autowired
    private PointOfSaleRepository repository;

    /**
     * Se encarga de generar el objeto con los datos de paginación para el punto de venta
     * @param page Contiene las características de la paginación
     * @param branchId Se paginarán puntos de venta relacionados ocn este ID
     * */
    public PagedResponse<PointOfSale> handlePointOfSaleList(Long branchId, Page page) {
        Pageable pageable = null;
        //Crea el objeto con las características de paginación
        if (page.getOrder().equals("asc")) {
            pageable = PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.ASC, page.getSort());
        }
        if (page.getOrder().equals("desc")) {
            pageable = PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.DESC, page.getSort());
        }

        //Trae al objeto Page una clase de utilidad para manejar la paginación
        var pages = repository.findByBranchOwnerBranchId(branchId, pageable);

        List<PointOfSale> content = pages.getNumberOfElements() == 0 ? Collections.emptyList() : pages.getContent();

        return new PagedResponse<PointOfSale>(content, pages.getNumber(), pages.getSize(),
                pages.getTotalElements(), pages.getTotalPages(), pages.isLast());
    }

}
