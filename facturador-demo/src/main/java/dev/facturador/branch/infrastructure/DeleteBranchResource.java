package dev.facturador.branch.infrastructure;

import dev.facturador.branch.domain.command.BranchDeleteCommand;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.noContent;


/**
 * EndPoint para eliminar la sucursal
 */
@RestController
@RequestMapping(path = "/api/branches")
public class DeleteBranchResource {
    private final PortCommandBus commandBus;

    public DeleteBranchResource(PortCommandBus commandBus) {
        this.commandBus = commandBus;
    }

    /**
     * Elimina una sucursal del trader solicitante
     *
     * @param IDBranch ID de la sucursal que se va a eliminar
     * @return Estado 204, no content
     * @throws Exception
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{IDBranch}")
    public Mono<HttpEntity<Void>> deleteBranch(@PathVariable(name = "IDBranch") long IDBranch) throws Exception {
        var command = BranchDeleteCommand.builder()
                .branchId(IDBranch).build();

        commandBus.handle(command);

        return Mono.empty().map(x-> noContent().build());
    }
}
