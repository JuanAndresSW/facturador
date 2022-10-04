package dev.facturador.branch.infrastructure;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.query.PagingBranchesQuery;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.global.domain.sharedpayload.Page;
import dev.facturador.global.domain.sharedpayload.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * EndPoint para paginar las sucursales
 */
@RestController
@RequestMapping(path = "/api/branches")
public class    PagingBranchesResource {
    private final PortQueryBus portQueryBus;

    @Autowired
    public PagingBranchesResource(PortQueryBus portQueryBus) {
        this.portQueryBus = portQueryBus;
    }

    /**
     * Devuelve todos los datos paginados de las {@link Branch} que le pertenezcan
     * al trader solicitante
     *
     * @param index    Número de la página que se está pidiendo
     * @param size     Tamaño de la pagina
     * @param sort     Nombre del atributo sobre el que estarán ordenados los elementos
     * @param order    Decide si el orden debe ser ASC(Ascendente) o DESC(Descendente)
     * @param IDTrader ID del comerciante que pide las sucursales
     * @return Estado 200, {@link PagedResponse} Contiene los datos necesarios para manejar la paginacion
     * @throws Exception
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/traders/{IDTrader}")
    public HttpEntity<PagedResponse<Branch>> getListBranch(
            @RequestParam(value = "index") int index,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order,
            @PathVariable(name = "IDTrader") long IDTrader) throws Exception {

        var query = PagingBranchesQuery.builder()
                .traderId(IDTrader)
                .page(Page.starter(index, size, sort, order))
                .build();

        var response = portQueryBus.handle(query);

        return ResponseEntity.ok().body(response);
    }
}
