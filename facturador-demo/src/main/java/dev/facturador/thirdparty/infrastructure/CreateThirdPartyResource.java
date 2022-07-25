package dev.facturador.thirdparty.infrastructure;

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

/**EndPoint para crear un cliente*/
@RestController
@RequestMapping(path = "/api/clients")
public class CreateThirdPartyResource {

    private final CommandBus commandBus;

    public CreateThirdPartyResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public HttpEntity<Void> addBranch(@Valid @RequestBody BranchCreate values) throws Exception {
        var command = BranchCreateCommand.Builder.getInstance()
                .branchCreate(values).build();

        commandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/clients")).build();
    }
}
