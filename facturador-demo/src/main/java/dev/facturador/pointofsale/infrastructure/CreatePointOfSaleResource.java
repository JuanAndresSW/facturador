package dev.facturador.pointofsale.infrastructure;

import dev.facturador.pointofsale.application.command.PointOfSaleCreateCommand;
import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import dev.facturador.shared.application.comandbus.CommandBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping(path = "/api/pointofsales")
public class CreatePointOfSaleResource {

    private CommandBus commandBus;

    public CreatePointOfSaleResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @PostMapping("/{IDBranch}")
    public HttpEntity<Void> addPointOfSale(@PathVariable(name = "IDBranch") long IDBranch,
                                           @Valid @RequestBody PointOfSaleCreate values) throws Exception {
        values.addID(IDBranch);
        log.info("Values is: {}", values);
        var command = PointOfSaleCreateCommand.Builder.getInstance()
                .pointOfSaleCreate(values).build();

        commandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/pointofsales")).build();
    }
}
