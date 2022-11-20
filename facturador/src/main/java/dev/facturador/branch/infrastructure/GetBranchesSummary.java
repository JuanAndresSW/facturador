package dev.facturador.branch.infrastructure;

import dev.facturador.branch.domain.query.BranchesSummaryQuery;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.LinkedList;

import static org.springframework.http.ResponseEntity.ok;

/**
 * EndPoint para recuperar algunos datos del comerciante
 */
@RestController
@RequestMapping(path = "/api/branches")
public class GetBranchesSummary {
    private final PortQueryBus queryBus;

    @Autowired
    public GetBranchesSummary(PortQueryBus queryBus) {
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
    @GetMapping("/traders/{IDTrader}/summary")
    public Mono<HttpEntity<LinkedList<HashMap<String, Object>>>> getBranchesSummary
    (@PathVariable(name = "IDTrader") long traderId) throws Exception {

        var query = BranchesSummaryQuery.builder()
                .traderId(traderId).build();

        var response = queryBus.handle(query);

        return Mono.just(response).map(r -> ok().body(r));

    }
}
