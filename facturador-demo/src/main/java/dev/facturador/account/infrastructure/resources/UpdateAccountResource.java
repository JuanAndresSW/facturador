package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.application.command.AccountUpdateCommand;
import dev.facturador.account.application.query.GetAccountQuery;
import dev.facturador.account.domain.AccountUpdate;
import dev.facturador.global.application.commands.CommandBus;
import dev.facturador.global.application.querys.QueryBus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**EndPoint para actualizar los datos de la cuenta de usuario*/
@RestController
@RequestMapping(path = "/api/accounts")
public class UpdateAccountResource {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public UpdateAccountResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    /**
     * Se encarga de intentar actualizar la cuenta
     * Si los datos enviados son validos
     *
     * @param accountForUpdate {@link AccountUpdate} contiene los nuevos datos de la cuenta
     * @return Estado 204 no content, si es correcto
     * @throws Exception
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public HttpEntity<Void> updateAccount(@Valid @RequestBody AccountUpdate accountForUpdate)
            throws Exception {
        var username = accountForUpdate.getUserUpdate().username();
        var query = GetAccountQuery.Builder.getInstance()
                .username(username).build();

        var user = queryBus.handle(query);

        var command = AccountUpdateCommand.Builder.getInstance()
                .mainAccountUpdate(accountForUpdate)
                .actualMainAccount(user).build();

        commandBus.handle(command);

        return ResponseEntity.noContent().build();
    }
}
