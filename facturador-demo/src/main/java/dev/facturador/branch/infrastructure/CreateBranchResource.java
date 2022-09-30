package dev.facturador.branch.infrastructure;

import dev.facturador.branch.domain.BranchCreateRestModel;
import dev.facturador.branch.domain.command.BranchCreateCommand;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 * EndPoint para crear la sucursal
 */
@RestController
@RequestMapping(path = "/api/branches")
public class CreateBranchResource {
    private final PortCommandBus portCommandBus;

    public CreateBranchResource(PortCommandBus portCommandBus) {
        this.portCommandBus = portCommandBus;
    }

    /**
     * Crea una sucursal nueva para el trader Solicitante
     *
     * @param branchRestModel {@link BranchCreateRestModel} Contiene los datos para crear la sucursal
     * @return Estado 201, Location {@code http:localhost:8080/api/branches}
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public HttpEntity<Void> addBranch(@Valid @RequestBody BranchCreateRestModel branchRestModel) throws Exception {
        var command = BranchCreateCommand.builder()
                .branchCreateRestModel(branchRestModel).build();

        portCommandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/branches")).build();
    }

}
