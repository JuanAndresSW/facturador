package dev.facturador.operation.fulls.infrastructure;

import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.operation.fulls.domain.model.FullOperationResponse;
import dev.facturador.operation.fulls.domain.commands.CreateAnyFullOperationCommand;
import dev.facturador.operation.fulls.domain.querys.GetRequiredOperationDataQuery;
import dev.facturador.operation.fulls.domain.model.FullOperationRestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

/**
 * EndPoint para crear una factura
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/operations/fulls")
public class CreateAnyFullOperationResource {
    private final PortCommandBus commandBus;
    private final PortQueryBus queryBus;

    @Autowired
    public CreateAnyFullOperationResource(PortQueryBus queryBus, PortCommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{repository}")
    public Mono<HttpEntity<Long>> addAnyFullOperation(
            @Valid @RequestBody FullOperationRestModel fullOperationRestModel,
            HttpServletRequest request,
            @NotNull @PathVariable(value = "repository") String repository) throws Exception {

        final var query = GetRequiredOperationDataQuery.builder()
                .pointOfSaleId(fullOperationRestModel.getIDPointOfSale())
                .traderId(fullOperationRestModel.getIDTrader())
                .header(request.getHeader("Authorization"))
                .receiverCategory(fullOperationRestModel.getReceiverVatCategory())
                .repository(repository).build();
        var response = queryBus.handle(query);

        var command = CreateAnyFullOperationCommand.builder()
                .invoiceValues(fullOperationRestModel)
                .internalValues(response)
                .repository(repository).build();
        commandBus.handle(command);

        return Mono.just(command.getId())
                .map(r -> created(URI.create(new StringBuilder
                        ("http:localhost:8080/api/operations/fulls/").append(repository).toString()))
                        .body(r));
    }

}
