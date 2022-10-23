package dev.facturador.pointofsale.infrastructure;

import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.pointofsale.domain.commands.PointOfSaleDeleteCommand;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPosGetQuery;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPosUpdateCommand;
import dev.facturador.pointofsale.domain.subdomain.RequiredPosControlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.noContent;

/**
 * EndPoint para eliminar puntos de venta
 */
@RestController
@RequestMapping(path = "/api/pointsofsale")
public class DeletePointOfSaleResource {
    private final PortCommandBus commandBus;
    private final PortQueryBus queryBus;

    @Autowired
    public DeletePointOfSaleResource(PortCommandBus commandBus, PortQueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    /**
     * Se encarga de eliminar un punto de venta
     * Si la eliminacion es exitosa actualiza el control del punto de venta
     * Al trader al que le perteneció el punto de venta borrado
     *
     * @param IDPointOfSale ID del punto de venta ha eliminar
     * @param IDTrader      ID al que le pertenece el punto de venta
     * @return Estado 204 no content
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{IDPointOfSale}/traders/{IDTrader}")
    public Mono<HttpEntity<Void>> deletePointOfSale(@PathVariable(name = "IDPointOfSale") long IDPointOfSale,
                                              @PathVariable(name = "IDTrader") long IDTrader)
            throws Exception {

        var command = PointOfSaleDeleteCommand.Builder.getInstance()
                .pointOfSaleId(IDPointOfSale).build();
        this.commandBus.handle(command);

        var query = ControlOfPosGetQuery.Builder.getInstance()
                .traderID(IDTrader).build();
        var control = queryBus.handle(query);

        var commandForControl = ControlOfPosUpdateCommand.Builder.getInstance()
                .data(RequiredPosControlData.starter(control.getPointsOfSaleControlId(), control.getCurrentCount(), control.getTotalCount(), false)).build();
        this.commandBus.handle(commandForControl);

        return Mono.empty().map(x-> noContent().build());
    }
}
