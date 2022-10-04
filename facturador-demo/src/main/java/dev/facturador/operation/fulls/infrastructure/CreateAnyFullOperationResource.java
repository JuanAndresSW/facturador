package dev.facturador.operation.fulls.infrastructure;

import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.operation.fulls.domain.commands.CreateAnyFullOperationCommand;
import dev.facturador.operation.fulls.domain.querys.GetRequiredOperationDataQuery;
import dev.facturador.operation.fulls.domain.model.FullOperationRestModel;
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
@RequestMapping(path = "/api/operations/fulls")
public class CreateAnyFullOperationResource {
    private final PortCommandBus portCommandBus;
    private final PortQueryBus portQueryBus;

    @Autowired
    public CreateAnyFullOperationResource(PortQueryBus portQueryBus, PortCommandBus portCommandBus) {
        this.portQueryBus = portQueryBus;
        this.portCommandBus = portCommandBus;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{repository}")
    public HttpEntity<Map<String, String>> addAnyFullOperation(
            @Valid @RequestBody FullOperationRestModel fullOperationRestModel,
            HttpServletRequest request,
            @NotNull @PathVariable(value = "repository") String repository) throws Exception {
       log.info("Entre al endPoint");
       log.info("RestModel is: {}", fullOperationRestModel);
        var query = GetRequiredOperationDataQuery.builder()
                .pointOfSaleId(fullOperationRestModel.getIDPointOfSale())
                .traderId(fullOperationRestModel.getIDTrader())
                .header(request.getHeader("Authorization"))
                .receiverCategory(fullOperationRestModel.getReceiverVatCategory())
                .repository(repository).build();
        log.info("Cree la query");
        var response = portQueryBus.handle(query);
        log.info("Pase al query");
        var command = CreateAnyFullOperationCommand.builder()
                .invoiceValues(fullOperationRestModel)
                .internalValues(response)
                .repository(repository).build();
        portCommandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/invoices"))
                .body(Map.of(
                        "operationNumber", response.getOperationNumber(),
                        "type", response.getType().toString()));
    }

}
