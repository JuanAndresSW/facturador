package dev.facturador.operation.wholeoperation.application.handlers;

import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import dev.facturador.global.domain.abstractcomponents.querys.QueryHandler;
import dev.facturador.operation.wholeoperation.application.CreditNoteRepository;
import dev.facturador.operation.wholeoperation.application.DebitNoteRepository;
import dev.facturador.operation.wholeoperation.application.InvoiceRepository;
import dev.facturador.operation.wholeoperation.domain.model.OperationCount;
import dev.facturador.operation.wholeoperation.domain.querys.GetRequiredOperationDataQuery;
import dev.facturador.operation.wholeoperation.domain.model.DataReququiredOperation;
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
public class GetRequiredOperationDataQueryHandler implements QueryHandler<DataReququiredOperation, GetRequiredOperationDataQuery> {
    @Autowired
    private final InvoiceRepository invoiceRepository;
    @Autowired
    private final CreditNoteRepository creditRepository;
    @Autowired
    private final DebitNoteRepository debitRepository;
    @Autowired
    private final ReactiveRequest<Class, HashMap<String, Object>> reactiveRequest;

    @Override
    public DataReququiredOperation handle(GetRequiredOperationDataQuery query) throws Exception {
        var request = reactiveRequest.makeRequest(
                "GET",
                "/api/pointsofsale/" + query.getPointOfSaleId(),
                null,
                MediaType.APPLICATION_JSON,
                HashMap.class,
                "Authorization",
                query.getHeader());

        var response = DataReququiredOperation.valueOf(request.getBody());
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
        return response.defineNumber(projection.get().getOperationNumberCount());
    }
}
