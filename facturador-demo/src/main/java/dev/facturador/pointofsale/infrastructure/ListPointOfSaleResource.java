package dev.facturador.pointofsale.infrastructure;

import dev.facturador.global.application.querys.QueryBus;
import dev.facturador.global.application.sharedpayload.Page;
import dev.facturador.global.application.sharedpayload.PagedResponse;
import dev.facturador.pointofsale.application.query.PointOfSaleListQuery;
import dev.facturador.pointofsale.domain.PointOfSale;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**EndPoint para Listar puntos de venta*/
@RestController
@RequestMapping(path = "/api/pointsofsale")
public class ListPointOfSaleResource {
    private final QueryBus queryBus;

    public ListPointOfSaleResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    /**
     * Se encarga ejecutar la Query que trae la paginacion de punto de venta
     * @param index Define el numero de pagina
     * @param size Define el tamaño de la pagina
     * @param sort Define por que parametro se va ordenar la lista
     * @param order Define si el orden es ascendente o descendente
     * @param branchID ID para listar los puntos de venta
     * @return Devuelve el objeto {@link PagedResponse} con los datos de paginacion
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/branch/{IDBranch}")
    public HttpEntity<PagedResponse<PointOfSale>> toListPointOfSale(
            @RequestParam(value = "index") int index,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order,
            @PathVariable(name = "IDBranch") long branchID) throws Exception {

        var query = PointOfSaleListQuery.Builder.getInstance()
                .page(Page.starter(index, size, sort, order))
                .branchId(branchID)
                .build();

        var response = queryBus.handle(query);

        return ResponseEntity.ok().body(response);
    }
}
