package dev.facturador.operation.ticket.infrastructure;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.operation.fulls.domain.model.FullOperationDisplayed;
import dev.facturador.operation.fulls.domain.querys.GetAnyFullOperationQuery;
import dev.facturador.operation.ticket.domain.TicketQuery;
import dev.facturador.operation.ticket.domain.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api/operations/ticket",produces = "application/json")
public class GetTicketResource {
    private final PortQueryBus queryBus;

    @Autowired
    public GetTicketResource(PortQueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public Mono<HttpEntity<TicketResponse>> getAnyFullOperation(@NotNull @RequestParam(value = "operationId") long operationId) throws Exception {
       final var query = TicketQuery.builder()
               .operationId(operationId)
               .build();

        var response = queryBus.handle(query);

        return Mono.just(response).map(r -> ok().body(r));
    }
}
