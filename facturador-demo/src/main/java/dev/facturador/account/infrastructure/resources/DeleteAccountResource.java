package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.domain.commands.AccountDeleteCommand;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;


/**
 * EndPoint para eliminar cuenta
 */
@RestController
@RequestMapping(path = "/api/accounts")
public class DeleteAccountResource {
    private final PortCommandBus portCommandBus;

    @Autowired
    public DeleteAccountResource(PortCommandBus portCommandBus) {
        this.portCommandBus = portCommandBus;
    }

    /**
     * @param username Nombre de usuario de la cuenta que se quiere eliminar
     * @return RespomseEmtity vació con el código 204
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{username}")
    public synchronized HttpEntity<Void> deleteAccount(@PathVariable @NotEmpty String username) throws Exception {

        var command = AccountDeleteCommand.builder()
                .username(username).build();

        portCommandBus.handle(command);

        return ResponseEntity.noContent().build();
    }
}
