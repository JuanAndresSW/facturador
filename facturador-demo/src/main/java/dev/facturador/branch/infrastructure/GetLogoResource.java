package dev.facturador.branch.infrastructure;

import dev.facturador.branch.domain.query.BranchGetQuery;
import dev.facturador.global.domain.abstractcomponents.query.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EndPoint para recuperar el Logo
 */
@RestController
@RequestMapping(path = "/api/branches")
public class GetLogoResource {
    private final QueryBus queryBus;

    @Autowired
    public GetLogoResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    /**
     * Recupera el Logo de la sucursal solicitada y lo envía
     *
     * @param IDBranch ID de la sucursal
     * @return El logo de la sucursal
     * @throws Exception
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{IDBranch}/get-logo")
    public HttpEntity<String> getLogo(@PathVariable(name = "IDBranch") long IDBranch)
            throws Exception {

        var query = BranchGetQuery.builder()
                .branchId(IDBranch).build();

        var branch = queryBus.handle(query);

        return ResponseEntity.ok().body(branch.getLogo());
    }

}
