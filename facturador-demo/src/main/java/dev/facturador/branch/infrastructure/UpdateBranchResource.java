package dev.facturador.branch.infrastructure;

import dev.facturador.branch.application.command.update.BranchUpdateCommand;
import dev.facturador.branch.application.query.get.BranchGetQuery;
import dev.facturador.branch.domain.BranchUpdate;
import dev.facturador.global.application.commands.CommandBus;
import dev.facturador.global.application.querys.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/branches")
public class UpdateBranchResource {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public UpdateBranchResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{IDBranch}")
    public HttpEntity<Void> updateBranch(@PathVariable(name = "IDBranch") long IDBranch,
                                         @Valid @RequestBody BranchUpdate values) throws Exception {
        var query = BranchGetQuery.Builder.getInstance()
                .branchId(IDBranch).build();

        var branch = queryBus.handle(query);

        var command = BranchUpdateCommand.Builder.getInstance()
                .branchUpdate(values).branch(branch).build();

        commandBus.handle(command);

        return ResponseEntity.ok().build();
    }
}
