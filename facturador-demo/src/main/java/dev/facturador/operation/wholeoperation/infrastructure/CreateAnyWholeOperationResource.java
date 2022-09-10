package dev.facturador.operation.wholeoperation.infrastructure;

import dev.facturador.global.domain.abstractcomponents.commands.CommandBus;
import dev.facturador.global.domain.abstractcomponents.querys.QueryBus;
import dev.facturador.operation.wholeoperation.domain.commands.CreateAnyWholeOperationCommand;
import dev.facturador.operation.wholeoperation.domain.querys.GetRequiredOperationDataQuery;
import dev.facturador.operation.wholeoperation.domain.model.WholeOperationRestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Map;

/**
 * EndPoint para crear una factura
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/wholes/operations")
public class CreateAnyWholeOperationResource {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @Autowired
    public CreateAnyWholeOperationResource(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/repositories/{repository}")
    public HttpEntity<Map<String, String>> addAnyWholeOperation(
            @Valid @RequestBody WholeOperationRestModel invoiceRestModel,
            HttpServletRequest request,
            @NotNull @PathVariable(value = "repository") String repository) throws Exception {

        var query = GetRequiredOperationDataQuery.builder()
                .pointOfSaleId(invoiceRestModel.getIDPointOfSale())
                .traderId(invoiceRestModel.getIDTrader())
                .header(request.getHeader("Authorization"))
                .receiverCategory(invoiceRestModel.getReceiverVatCategory())
                .repository(repository)
                .build();

        var response = queryBus.handle(query);

        var command = CreateAnyWholeOperationCommand.builder()
                .invoiceValues(invoiceRestModel)
                .internalValues(response)
                .repository(repository).build();

        commandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/invoices"))
                .body(Map.of(
                        "operationNumber", response.getOperationNumber(),
                        "type", response.getType().toString()));
    }

}
