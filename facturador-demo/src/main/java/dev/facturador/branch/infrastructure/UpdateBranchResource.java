package dev.facturador.branch.infrastructure;

import dev.facturador.branch.domain.BranchUpdateRestModel;
import dev.facturador.branch.domain.command.BranchUpdateCommand;
import dev.facturador.branch.domain.query.BranchGetQuery;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * EndPoint para actualizar la sucursal
 */
@RestController
@RequestMapping(path = "/api/branches")
public class UpdateBranchResource {
    private final PortCommandBus portCommandBus;
    private final PortQueryBus portQueryBus;

    public UpdateBranchResource(PortCommandBus portCommandBus, PortQueryBus portQueryBus) {
        this.portCommandBus = portCommandBus;
        this.portQueryBus = portQueryBus;
    }

    /**
     * Se encarga de realizar la actualización de la sucursal
     *
     * @param IDBranch        ID de la sucursal que se quiere actualizar
     * @param branchRestModel Los nuevos datos de la sucursal
     * @return 204 no content
     * @throws Exception
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{IDBranch}")
    public HttpEntity<Void> updateBranch(@PathVariable(name = "IDBranch") long IDBranch,
                                         @Valid @RequestBody BranchUpdateRestModel branchRestModel) throws Exception {
        var query = BranchGetQuery.builder()
                .branchId(IDBranch).build();

        var branch = portQueryBus.handle(query);

        var command = BranchUpdateCommand.builder()
                .branchUpdateRestModel(branchRestModel).branch(branch).build();

        portCommandBus.handle(command);

        return ResponseEntity.noContent().build();
    }
}
