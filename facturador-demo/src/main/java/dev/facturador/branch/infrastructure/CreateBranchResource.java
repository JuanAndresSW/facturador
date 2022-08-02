package dev.facturador.branch.infrastructure;

import dev.facturador.branch.application.command.BranchCreateCommand;
import dev.facturador.branch.domain.BranchCreate;
import dev.facturador.global.application.commands.CommandBus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**EndPoint para crear la sucursal*/
@RestController
@RequestMapping(path = "/api/branches")
public class CreateBranchResource {

    private final CommandBus commandBus;

    public CreateBranchResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    /**
     * Crea una sucursal nueva para el trader Solicitante
     *
     * @param branchValues {@link BranchCreate} Contiene los datos para crear la sucursal
     * @return Estado 201, Location {@code http:localhost:8080/api/branches}
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public HttpEntity<Void> addBranch(@Valid @RequestBody BranchCreate branchValues) throws Exception {
        var command = BranchCreateCommand.Builder.getInstance()
                .branchCreate(branchValues).build();

        commandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/branches")).build();
    }

}
