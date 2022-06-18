package dev.facturador.branch.infrastructure;

import dev.facturador.branch.application.command.delete.BranchDeleteCommand;
import dev.facturador.global.application.commands.CommandBus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**EndPoint para eliminar la sucursal*/
@RestController
@RequestMapping(path = "/api/branches")
public class DeleteBranchResource {
    private final CommandBus commandBus;

    public DeleteBranchResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    /**Ejecuta el comando para eliminar una sucursal*/
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{IDBranch}")
    public HttpEntity<Void> deleteBranch(@PathVariable(name = "IDBranch") long IDBranch) throws Exception {
        var command = BranchDeleteCommand.Builder.getInstance()
                .branchId(IDBranch).build();

        commandBus.handle(command);

        return ResponseEntity.noContent().build();
    }
}
