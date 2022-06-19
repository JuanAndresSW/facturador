package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.application.command.update.AccountUpdateCommand;
import dev.facturador.account.application.query.get.AccountGetQuery;
import dev.facturador.account.domain.AccountUpdate;
import dev.facturador.global.application.commands.CommandBus;
import dev.facturador.global.application.querys.QueryBus;
import lombok.extern.slf4j.Slf4j;
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
     * Se ejecuta una Query para obtener los datos de la cuenta y comprobar que no sean repetidos
     * Se ejecuta el comando con los nuevos datos y los datos antiguos para actualizarlos
     * @param accountForUpdate Objeto {@link AccountUpdate} contiene los nuevos datos de la cuenta
     * */
    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public HttpEntity<Void> updateAccount(@Valid @RequestBody AccountUpdate accountForUpdate)
            throws Exception {
        String username = accountForUpdate.getUserUpdate().username();
        AccountGetQuery query = AccountGetQuery.Builder.getInstance()
                .username(username).build();

        var user = queryBus.handle(query);

        AccountUpdateCommand command = AccountUpdateCommand.Builder.getInstance()
                .mainAccountUpdate(accountForUpdate)
                .actualMainAccount(user).build();

        commandBus.handle(command);

        return ResponseEntity.noContent().build();
    }
}
