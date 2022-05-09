package dev.facturador.branch.infrastructure;

import dev.facturador.branch.application.command.update.BranchUpdateCommand;
import dev.facturador.branch.application.query.get.BranchGetQuery;
import dev.facturador.branch.domain.BranchID;
import dev.facturador.branch.domain.BranchUpdate;
import dev.facturador.shared.application.comandbus.CommandBus;
import dev.facturador.shared.application.querybus.QueryBus;
import dev.facturador.shared.domain.CurrentUser;
import dev.facturador.shared.domain.CustomUserDetails;
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
    private CommandBus commandBus;
    private QueryBus queryBus;

    public UpdateBranchResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @PutMapping("/{IDBranch}")
    public HttpEntity<Void> updateBranch(@PathVariable(name = "IDBranch") long IDBranch,
                                         @Valid @RequestBody BranchUpdate values) throws Exception {
        var query = BranchGetQuery.Builder.getInstance()
                        .branchID(BranchID.valueof(IDBranch)).build();

        var branch = queryBus.handle(query);
        log.info("Pase la query");
        log.info("Branch Is: {}", branch);
        log.info("Branch Photo Is: {}", branch.getPhoto());
        var command = BranchUpdateCommand.Builder.getInstance()
                .branchUpdate(values).branch(branch).build();
        log.info("Pase el comando. Body is: {}", values);
        commandBus.handle(command);

        return ResponseEntity.ok().build();
    }
}
