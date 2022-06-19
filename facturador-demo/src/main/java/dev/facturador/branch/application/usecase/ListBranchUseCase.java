package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchRepository;
import dev.facturador.global.application.sharedpayload.Page;
import dev.facturador.global.application.sharedpayload.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**Caso de uso para paginar la sucursal*/
@Service
public class ListBranchUseCase {

    @Autowired
    private BranchRepository repository;
    /**
     * Se encarga de generar el objeto con los datos de paginación para la sucursal
     * @param page Contiene las características de la paginación
     * @param traderId Se paginarán las sucursales relacionadas con este trader
     * */
    public PagedResponse<Branch> handleBranchList(Long traderId, Page page) {
        Pageable pageable = null;
        //Crea el objeto con las características de paginación
        if (page.getOrder().equals("asc")) {
            pageable = PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.ASC, page.getSort());
        }
        if (page.getOrder().equals("desc")) {
            pageable = PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.DESC, page.getSort());
        }
        //Trae al objeto Page una clase de utilidad para manejar la paginación
        var pages = repository.findByTraderOwnerTraderId(traderId, pageable);
        //Como no necesito estos atributo de Branch lo marco como null para que no molesten
        if (!pages.getContent().isEmpty()) {
            pages.getContent().forEach(branch -> {
                branch.setPointsOfSaleCreated(null);
                branch.setLogo(null);
            });
        }
        //Recupero el listado de branch
        List<Branch> content = pages.getNumberOfElements() == 0 ? Collections.emptyList() : pages.getContent();
        //Creo el objeto de paginacion
        return new PagedResponse<Branch>(content, pages.getNumber(), pages.getSize(),
                pages.getTotalElements(), pages.getTotalPages(), pages.isLast());
    }
}
