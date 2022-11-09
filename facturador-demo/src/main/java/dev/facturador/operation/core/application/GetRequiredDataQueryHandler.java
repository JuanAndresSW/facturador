package dev.facturador.operation.core.application;

import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.operation.fulls.application.CreditNoteRepository;
import dev.facturador.operation.fulls.application.DebitNoteRepository;
import dev.facturador.operation.fulls.application.InvoiceRepository;
import dev.facturador.operation.fulls.domain.model.OperationCount;
import dev.facturador.operation.core.domain.GetRequiredOperationDataQuery;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
import dev.facturador.operation.ticket.application.TicketRepository;
import dev.facturador.trader.domain.Trader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class GetRequiredDataQueryHandler implements PortQueryHandler<DataRequiredOperation, GetRequiredOperationDataQuery> {
    @Autowired
    private final InvoiceRepository invoiceRepository;
    @Autowired
    private final CreditNoteRepository creditRepository;
    @Autowired
    private final DebitNoteRepository debitRepository;
    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private final ReactiveRequest<Class, HashMap<String, Object>> reactiveRequest;

    @Override
    public DataRequiredOperation handle(GetRequiredOperationDataQuery query) throws Exception {
        var request = reactiveRequest.makeRequest(
                "GET",
                "/api/pointsofsale/" + query.getPointOfSaleId(),
                null,
                MediaType.APPLICATION_JSON,
                HashMap.class,
                "Authorization",
                query.getHeader());
        var response = DataRequiredOperation.valueOf(request.getBody());
        var number = this.findOperationNumberCount
                (query.getRepository(),response,query.getCategory(), new Trader(query.getTraderId()));

        if( Objects.isNull(number.get().getOperationNumberCount())) return response.defineNumber(0);
        return response.defineNumber(number.get().getOperationNumberCount());
    }

    private Optional<OperationCount> findOperationNumberCount(String repository, DataRequiredOperation response,String category, Trader trader){
        Optional<OperationCount> projection = Optional.empty();
        if(repository.equals("invoice")) {
            response.resolveType(category);
            projection = invoiceRepository.findOperationNumberCount(response.getType(), trader, response.getPointOfSaleNumber());
        }
        if(repository.contains("debit")) {
            response.resolveType(category);
            projection = debitRepository.findOperationNumberCount(response.getType(), trader, response.getPointOfSaleNumber());
        }
        if(repository.contains("credit")) {
            response.resolveType(category);
            projection = creditRepository.findOperationNumberCount(response.getType(), trader, response.getPointOfSaleNumber());
        }
        if(repository.equals("ticket")) {
            projection = ticketRepository.findOperationNumberCount(trader, response.getPointOfSaleNumber());
        }

        return projection;
    }
}