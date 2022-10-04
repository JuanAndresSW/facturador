package dev.facturador.operation.fulls.application.handlers;

import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.operation.fulls.application.CreditNoteRepository;
import dev.facturador.operation.fulls.application.DebitNoteRepository;
import dev.facturador.operation.fulls.application.InvoiceRepository;
import dev.facturador.operation.fulls.domain.model.OperationCount;
import dev.facturador.operation.fulls.domain.querys.GetRequiredOperationDataQuery;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
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
        log.info("Pase la segunda request");
        var response = DataRequiredOperation.valueOf(request.getBody());
        log.info("Cree el response");
        response.category(query.getReceiverCategory());

        Optional<OperationCount> projection = Optional.empty();
        if(query.getRepository().equals("invoice")){
            projection = invoiceRepository.findOperationNumberCount(response.getType(), new Trader(query.getTraderId()), response.getPointOfSaleNumber());
        }
        if(query.getRepository().contains("debit")) {
            projection = debitRepository.findOperationNumberCount(response.getType(), new Trader(query.getTraderId()), response.getPointOfSaleNumber());
        }
        if(query.getRepository().contains("credit")) {
            projection = creditRepository.findOperationNumberCount(response.getType(), new Trader(query.getTraderId()), response.getPointOfSaleNumber());
        }
        if( Objects.isNull(projection.get().getOperationNumberCount())) {
            return response.defineNumber(0);
        }
        log.info("Apunto de enviar la request");
        return response.defineNumber(projection.get().getOperationNumberCount());
    }
}
