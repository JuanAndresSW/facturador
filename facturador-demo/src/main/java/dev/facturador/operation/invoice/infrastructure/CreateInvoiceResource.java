package dev.facturador.operation.invoice.infrastructure;

import dev.facturador.global.domain.abstractcomponents.commands.CommandBus;
import dev.facturador.global.domain.abstractcomponents.querys.QueryBus;
import dev.facturador.operation.invoice.domain.commands.CreateInvoiceCommand;
import dev.facturador.operation.invoice.domain.querys.GetInvoiceNumberQuery;
import dev.facturador.operation.shared.domain.model.WholeOperationRestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * EndPoint para crear una factura
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/invoices")
public class CreateInvoiceResource {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @Autowired
    public CreateInvoiceResource(QueryBus queryBus, CommandBus commandBus) {
        this.queryBus = queryBus;
        this.commandBus = commandBus;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public HttpEntity<Map<String, String>> addInvoice(@Valid @RequestBody WholeOperationRestModel invoiceRestModel, HttpServletRequest request) throws Exception {
        var query = GetInvoiceNumberQuery.builder()
                .pointOfSaleId(invoiceRestModel.getIDPointOfSale())
                .traderId(invoiceRestModel.getIDTrader())
                .header(request.getHeader("Authorization"))
                .receiverCategory(invoiceRestModel.getReceiverVatCategory())
                .build();

        var response = queryBus.handle(query);

        var command = CreateInvoiceCommand.builder()
                .invoiceValues(invoiceRestModel).internalValues(response).build();
        commandBus.handle(command);

        return ResponseEntity.created(new URI("http:localhost:8080/api/invoices"))
                .body(Map.of(
                        "operationNumber", response.getOperationNumber(),
                        "type", response.getType().toString()));
    }

}
