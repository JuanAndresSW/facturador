package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.application.query.get.AccountGetQuery;
import dev.facturador.account.domain.AccountTraderData;
import dev.facturador.global.application.querys.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/** EndPoint para recuperar algunos datos del comerciante*/
@RestController
@RequestMapping(path = "/api/accounts")
public class GetPartOfTraderResource {
    private final QueryBus queryBus;

    public GetPartOfTraderResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    /**
     *  Se encarga de llamar al manejador de Qurys para ejecutar la query
     * @param username Se consiguen los datos con el username
     * @return Datos del trader: nombre de comerciante, categoría de vat y CUIT
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}")
    public HttpEntity<AccountTraderData> getTraderData(@PathVariable @NotEmpty String username) throws Exception {
        AccountGetQuery query = AccountGetQuery.Builder.getInstance()
                .username(username).build();

        var user = queryBus.handle(query);
        var response = AccountTraderData.valueOf(user);

        return ResponseEntity.ok().body(response);

    }
}
