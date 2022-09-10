package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.domain.RequiredTraderData;
import dev.facturador.account.domain.querys.GetAccountQuery;
import dev.facturador.global.domain.abstractcomponents.query.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * EndPoint para recuperar un resumen del comerciante
 */
@RestController
@RequestMapping(path = "/api/accounts")
public class GetTraderSummary {
    private final QueryBus queryBus;

    @Autowired
    public GetTraderSummary(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    /**
     * Recupera un resumen del Trader
     *
     * @param username El username del comerciante  que se busca
     * @return Devuelve {@link RequiredTraderData} contiene los datos solicitados
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}")
    public HttpEntity<RequiredTraderData> getTraderSummary(@PathVariable @NotEmpty String username) throws Exception {
        var query = GetAccountQuery.builder()
                .username(username).build();

        var user = queryBus.handle(query);
        var response = RequiredTraderData.valueOf(user);

        return ResponseEntity.ok().body(response);

    }
}
