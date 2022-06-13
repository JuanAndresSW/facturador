package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.mainaccount.application.command.delete.MainAccountDeleteCommand;
import dev.facturador.mainaccount.domain.MainAccountIdUsername;
import dev.facturador.shared.application.commands.CommandBus;
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
@RequestMapping(path = "/api/mainaccounts")
public class DeleteMainAccountResource {
    private CommandBus commandBus;

    public DeleteMainAccountResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @DeleteMapping("/{username}")
    public HttpEntity<Void> deleteMainAccount(@PathVariable @NotEmpty String username) throws Exception {
        MainAccountDeleteCommand command = MainAccountDeleteCommand.Builder.getInstance()
                .mainAccountIdUsername(MainAccountIdUsername.starter(username)).build();

        commandBus.handle(command);
        return ResponseEntity.ok().build();
    }
}
