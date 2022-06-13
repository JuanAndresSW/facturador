package dev.facturador.pointofsale.infrastructure;

import dev.facturador.pointofsale.application.command.PointOfSaleCreateCommand;
import dev.facturador.pointofsale.application.subdomain.command.ControlOfPosUpdateCommand;
import dev.facturador.pointofsale.application.subdomain.query.ControlOfPosGetQuery;
import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPosData;
import dev.facturador.shared.application.commands.CommandBus;
import dev.facturador.shared.application.querys.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping(path = "/api/pointsofsale")
public class CreatePointOfSaleResource {

    private CommandBus commandBus;
    private QueryBus queryBus;

    public CreatePointOfSaleResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @PostMapping
    public HttpEntity<Void> addPointOfSale(@RequestParam(name = "IDTrader") long IDTrader,
                                           @RequestParam(name = "IDBranch") long IDBranch) throws Exception {

        var query = ControlOfPosGetQuery.Builder.getInstance()
                .traderID(IDTrader).build();
        var control = queryBus.handle(query);
        log.info("Recupere el control");
        var command = PointOfSaleCreateCommand.Builder.getInstance()
                .pointOfSaleCreate(PointOfSaleCreate.create(IDBranch, IDTrader, control.getTotalCount())).build();

        commandBus.handle(command);
        log.info("Cree el punto de venta");
        var commandForControl = ControlOfPosUpdateCommand.Builder.getInstance()
                .data(ControlOfPosData.starter(control.getControlOfPosId(), control.getCurrentCount(), control.getTotalCount(), true)).build();

        this.commandBus.handle(commandForControl);
        log.info("Actualize el control");
        return ResponseEntity.created(new URI("http:localhost:8080/api/pointsofsale")).build();
    }
}
