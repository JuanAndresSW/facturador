package dev.facturador.branch.infrastructure;

import dev.facturador.global.application.querys.QueryBus;
import dev.facturador.branch.application.query.BranchesSummaryQuery;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;

/** EndPoint para recuperar algunos datos del comerciante*/
@RestController
@RequestMapping(path = "/api/branches")
public class GetBranchesSummary {
    private final QueryBus queryBus;

    public GetBranchesSummary(QueryBus queryBus) {
        this.queryBus = queryBus;
    }


    /**
     * Devuelve un resumen de todas las sucursales
     * Que le pertenezcan al {@code Comerciante} solicitante
     *
     * @param traderId ID del comerciante que tiene las sucursales
     * @return Envía una lista con un Mapa con estos datos de la sucursal {@link dev.facturador.branch.domain.BranchSummaryProjection}
     * @throws Exception
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/traders/{IDTrader}")
    public HttpEntity<LinkedList<HashMap<String, Object>>> getBranchesSummary
    (@PathVariable(name = "IDTrader") long traderId) throws Exception {

        var query = BranchesSummaryQuery.Builder.getInstance()
                .traderId(traderId).build();

        var response = queryBus.handle(query);

        return ResponseEntity.ok().body(response);

    }
}
