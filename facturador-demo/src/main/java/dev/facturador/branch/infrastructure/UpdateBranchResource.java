package dev.facturador.branch.infrastructure;

import dev.facturador.branch.domain.BranchUpdateRestModel;
import dev.facturador.branch.domain.command.BranchUpdateCommand;
import dev.facturador.branch.domain.query.BranchGetQuery;
import dev.facturador.global.domain.abstractcomponents.command.CommandBus;
import dev.facturador.global.domain.abstractcomponents.query.QueryBus;
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
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public UpdateBranchResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
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

        var branch = queryBus.handle(query);

        var command = BranchUpdateCommand.builder()
                .branchUpdateRestModel(branchRestModel).branch(branch).build();

        commandBus.handle(command);

        return ResponseEntity.noContent().build();
    }
}
