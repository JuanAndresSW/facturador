package dev.facturador.pointofsale.infrastructure;

import dev.facturador.global.application.commands.CommandBus;
import dev.facturador.global.application.querys.QueryBus;
import dev.facturador.pointofsale.application.command.PointOfSaleCreateCommand;
import dev.facturador.pointofsale.application.subdomain.query.ControlOfPosGetQuery;
import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**EndPoint para crear un punto de venta*/
@RestController
@RequestMapping(path = "/api/pointsofsale")
public class CreatePointOfSaleResource {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CreatePointOfSaleResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
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
        var control = queryBus.handle(query);


        var command = PointOfSaleCreateCommand.Builder.getInstance()
                .pointOfSaleCreate(PointOfSaleCreate.valueOf(IDBranch, IDTrader, control)).build();

        commandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/pointsofsale")).build();
    }
}
