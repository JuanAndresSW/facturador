package dev.facturador.mainaccount.infrastructure;

import dev.facturador.mainaccount.application.command.update.MainAccountUpdateCommand;
import dev.facturador.mainaccount.application.query.MainAccountGetQuery;
import dev.facturador.mainaccount.domain.MainAccountIdUsername;
import dev.facturador.mainaccount.domain.MainAccountUpdate;
import dev.facturador.shared.application.comandbus.CommandBus;
import dev.facturador.shared.application.querybus.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/mainaccounts")
public class UpdateMainAccountResource {
    private CommandBus commandBus;
    private QueryBus queryBus;

    public UpdateMainAccountResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @PutMapping
    public HttpEntity update(@Valid @RequestBody MainAccountUpdate accountForUpdate)
            throws Exception {
        String username = accountForUpdate.getUserUpdate().username();
        MainAccountGetQuery query = MainAccountGetQuery.Builder.getInstance()
                .mainAccountIdUsername(MainAccountIdUsername.starter(username)).build();

        var user = queryBus.handle(query);

        MainAccountUpdateCommand command = MainAccountUpdateCommand.Builder.getInstance()
                .mainAccountUpdate(accountForUpdate)
                .actualMainAccount(user).build();

        commandBus.handle(command);

        return ResponseEntity.ok().build();
    }
}
