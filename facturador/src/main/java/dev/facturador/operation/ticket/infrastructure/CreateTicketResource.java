package dev.facturador.operation.ticket.infrastructure;

import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.operation.core.domain.GetRequiredOperationDataQuery;
import dev.facturador.operation.ticket.domain.TicketCommand;
import dev.facturador.operation.ticket.domain.TicketRestModel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;

import static org.springframework.http.ResponseEntity.created;

/**
 * EndPoint para crear una factura
 */
@Log4j2
@RestController
@RequestMapping(path = "/api/operations/ticket")
@AllArgsConstructor
public class CreateTicketResource {
    private final PortCommandBus commandBus;
    private final PortQueryBus queryBus;
    @Autowired
    private ReactiveRequest<Class, HashMap<String, Object>> reactiveRequest;

    @Autowired
    public CreateTicketResource(PortQueryBus queryBus, PortCommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public Mono<HttpEntity<Long>> addAnyFullOperation(
            @Valid @RequestBody TicketRestModel ticketRestModel,
            @RequestHeader(name = "Authorization") String token) throws Exception {
        final var query = GetRequiredOperationDataQuery.builder()
                .pointOfSaleId(Long.valueOf(ticketRestModel.getIDPointOfSale()))
                .traderId(Long.valueOf(ticketRestModel.getIDTrader()))
                .header(token)
                .repository("ticket").build();

        var response = queryBus.handle(query);

        var command = TicketCommand.builder()
                .products(ticketRestModel.getProducts())
                .requiredData(response)
                .traderId(Long.parseLong(ticketRestModel.getIDTrader()))
                .build();

        commandBus.handle(command);

        return Mono.just(command.getId())
                .map(r -> created(URI.create("http:localhost:8080/api/operations/ticket"))
                        .body(r));
    }

}
