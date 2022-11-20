package dev.facturador.operation.ticket.application;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.operation.core.application.OperationRepository;
import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.operation.core.domain.model.ProductModel;
import dev.facturador.operation.ticket.domain.TicketQuery;
import dev.facturador.operation.ticket.domain.TicketResponse;
import dev.facturador.security.domain.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class TicketQueryHandler implements PortQueryHandler<TicketResponse, TicketQuery> {
    @Autowired
    private final OperationRepository repository;

    @Override
    public TicketResponse handle(TicketQuery query) throws Exception {

        var operation = repository.findByOperationId(query.getOperationId());
        if (operation.isEmpty()) throw new ResourceNotFound("El comerciante no tiene esta operacion");

        return toDisplayed(operation.get());
    }

    private TicketResponse toDisplayed(Operation operation){
        var response = new TicketResponse();
        response.setSenderCuit(operation.getSender().getSenderCode());
        response.setSenderName(operation.getSender().getSenderName());
        response.setSenderVatCategory(operation.getSender().getSenderVatCategory().vatToLowercaseAndSpanish());
        response.setIssueDate(operation.getIssueDate().toString());
        response.setSenderAddress(operation.getSender().getSenderAddress());
        response.setOperationNumber(operation.getTicket().getTicketNumber());

        //Crear productos
        List<ProductModel> lista = new ArrayList<>();

        operation.getProducts().forEach(x ->
                lista.add(new ProductModel(x.getQuantity(), x.getPrice(), x.getDetail())));

        response.setProducts(lista);

        return response;
    }
}
