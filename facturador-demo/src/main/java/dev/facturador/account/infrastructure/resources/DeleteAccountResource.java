package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.application.command.delete.AccountDeleteCommand;
import dev.facturador.global.application.commands.CommandBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping(path = "/api/accounts")
public class DeleteAccountResource {
    private final CommandBus commandBus;

    public DeleteAccountResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{username}")
    public HttpEntity<Void> deleteAccount(@PathVariable @NotEmpty String username) throws Exception {

        AccountDeleteCommand command = AccountDeleteCommand.Builder.getInstance()
                .mainAccountIdUsername(username).build();

        commandBus.handle(command);
        return ResponseEntity.noContent().build();
    }
}
