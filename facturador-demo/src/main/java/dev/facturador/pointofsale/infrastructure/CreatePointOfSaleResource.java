package dev.facturador.pointofsale.infrastructure;

import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.pointofsale.domain.PointOfSaleModel;
import dev.facturador.pointofsale.domain.commands.PointOfSaleCreateCommand;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPosGetQuery;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * EndPoint para crear un punto de venta
 */
@RestController
@RequestMapping(path = "/api/pointsofsale")
public class CreatePointOfSaleResource {
    private final PortCommandBus portCommandBus;
    private final PortQueryBus portQueryBus;

    public CreatePointOfSaleResource(PortCommandBus portCommandBus, PortQueryBus portQueryBus) {
        this.portCommandBus = portCommandBus;
        this.portQueryBus = portQueryBus;
    }

    /**
     * Crea un nuevo punto de venta.
     * Si la creación es exitosa actualiza el control de puntos de venta.
     * del trader al que le pertenezca el punto de venta creado.
     *
     * @param IDTrader ID del trader para actualizar el PointOfSaleControl
     * @param IDBranch ID de la sucursal que crea el punto de venta
     * @return Estado 201 created, Location "http:localhost:8080/api/pointsofsale
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/branch/{IDBranch}/trader/{IDTrader}")
    public HttpEntity<Void> addPointOfSale(@PathVariable(name = "IDTrader") long IDTrader,
                                           @PathVariable(name = "IDBranch") long IDBranch) throws Exception {

        var query = ControlOfPosGetQuery.Builder.getInstance()
                .traderID(IDTrader).build();
        var control = portQueryBus.handle(query);


        var command = PointOfSaleCreateCommand.Builder.getInstance()
                .pointOfSaleCreate(PointOfSaleModel.valueOf(IDBranch, IDTrader, control)).build();

        portCommandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/pointsofsale")).build();
    }
}
