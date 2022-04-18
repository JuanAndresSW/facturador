package dev.facturador.branch.infrastructure;

import dev.facturador.branch.application.command.delete.BranchDeleteCommand;
import dev.facturador.branch.domain.BranchID;
import dev.facturador.branch.domain.exception.BranchNotFound;
import dev.facturador.shared.application.comandbus.CommandBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(path = "/api/branches")
public class DeleteBranchResource {
    private CommandBus commandBus;

    public DeleteBranchResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @DeleteMapping("/{IDBranch}")
    public HttpEntity<Void> deleteBranch(@PathVariable(name = "IDBranch") long IDBranch) throws Exception {
        var command = BranchDeleteCommand.Builder.getInstance()
                        .branchID(BranchID.valueof(IDBranch)).build();

        commandBus.handle(command);

        return ResponseEntity.ok().build();
    }
}
