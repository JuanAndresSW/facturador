package dev.facturador.pointofsale.infrastructure;

import dev.facturador.global.application.commands.CommandBus;
import dev.facturador.global.application.querys.QueryBus;
import dev.facturador.pointofsale.application.command.PointOfSaleDeleteCommand;
import dev.facturador.pointofsale.application.subdomain.command.ControlOfPosUpdateCommand;
import dev.facturador.pointofsale.application.subdomain.query.ControlOfPosGetQuery;
import dev.facturador.pointofsale.domain.subdomain.PosControlData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/pointsofsale")
public class DeletePointOfSaleResource {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public DeletePointOfSaleResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{IDPointOfSale}/trader/{IDTrader}")
    public HttpEntity<Void> deletePointOfSale(@PathVariable(name = "IDPointOfSale") long IDPointOfSale,
                                              @PathVariable(name = "IDTrader") long IDTrader)
            throws Exception {

        var command = PointOfSaleDeleteCommand.Builder.getInstance()
                .pointOfSaleId(IDPointOfSale).build();

        this.commandBus.handle(command);

        var query = ControlOfPosGetQuery.Builder.getInstance()
                .traderID(IDTrader).build();
        var control = queryBus.handle(query);

        var commandForControl = ControlOfPosUpdateCommand.Builder.getInstance()
                .data(PosControlData.starter(control.getPointsOfSaleControlId(), control.getCurrentCount(), control.getTotalCount(), false)).build();

        this.commandBus.handle(commandForControl);

        return ResponseEntity.noContent().build();
    }
}
