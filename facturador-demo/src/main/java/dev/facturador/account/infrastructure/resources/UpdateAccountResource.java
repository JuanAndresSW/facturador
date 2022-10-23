package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.domain.AccountUpdateRestModel;
import dev.facturador.account.domain.commands.AccountUpdateCommand;
import dev.facturador.account.domain.querys.GetAccountQuery;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.noContent;

/**
 * EndPoint para actualizar los datos de la cuenta de usuario
 */
@RestController
@RequestMapping(path = "/api/accounts")
public class UpdateAccountResource {
    private final PortCommandBus portCommandBus;
    private final PortQueryBus portQueryBus;

    public UpdateAccountResource(PortCommandBus portCommandBus, PortQueryBus portQueryBus) {
        this.portCommandBus = portCommandBus;
        this.portQueryBus = portQueryBus;
    }

    /**
     * Se encarga de intentar actualizar la cuenta
     * Si los datos enviados son validos
     *
     * @param accountRestModel {@link AccountUpdateRestModel} contiene los nuevos datos de la cuenta
     * @return Estado 204 no content, si es correcto
     * @throws Exception
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public Mono<HttpEntity<Void> > updateAccount(@Valid @RequestBody AccountUpdateRestModel accountRestModel)
            throws Exception {
        var username = accountRestModel.getUserUpdate().username();
        var query = GetAccountQuery.builder()
                .username(username).build();
        var user = portQueryBus.handle(query);

        var command = AccountUpdateCommand.builder()
                .accountUpdateRestModel(accountRestModel)
                .actualAccount(user).build();
        portCommandBus.handle(command);

        return Mono.empty().map(x -> noContent().build());
    }
}
