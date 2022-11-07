package dev.facturador.operation.ticket.infrastructure;

import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
import dev.facturador.operation.ticket.domain.TicketCommand;
import dev.facturador.operation.ticket.domain.TicketRestModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
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
@Slf4j
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
            HttpServletRequest request) throws Exception {

        log.info("HOLA MUNDO: {}", ticketRestModel);
        var response = reactiveRequest.makeRequest(
                "GET",
                "/api/pointsofsale/" + ticketRestModel.getIDPointOfSale(),
                null,
                MediaType.APPLICATION_JSON,
                HashMap.class,
                "Authorization",
                request.getHeader("Authorization"));

        log.info("PASE EL POINT REQ");
        var required = DataRequiredOperation.valueOf(response.getBody());
        log.info("Pase required data");
        var command = TicketCommand.builder()
                .products(ticketRestModel.getProducts())
                .requiredData(required)
                .traderId(Long.parseLong(ticketRestModel.getIDTrader()))
                .build();

        commandBus.handle(command);

        return Mono.just(command.getId())
                .map(r -> created(URI.create("http:localhost:8080/api/operations/ticket"))
                        .body(r));
    }

}
