package dev.facturador.operation.invoice.application.handlers;

import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import dev.facturador.global.domain.abstractcomponents.querys.QueryHandler;
import dev.facturador.operation.invoice.application.InvoiceRepository;
import dev.facturador.operation.invoice.domain.querys.GetInvoiceNumberQuery;
import dev.facturador.operation.shared.domain.model.DataReququiredOperation;
import dev.facturador.trader.domain.Trader;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@AllArgsConstructor
@Service
public class GetInvoiceNumberQueryHandler implements QueryHandler<DataReququiredOperation, GetInvoiceNumberQuery> {
    @Autowired
    private final InvoiceRepository repository;
    @Autowired
    private final ReactiveRequest<Class, HashMap<String, Object>> reactiveRequest;

    @Override
    public DataReququiredOperation handle(GetInvoiceNumberQuery query) throws Exception {
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

        var projection = repository.findOperationNumberCount(response.getType(), new Trader(query.getTraderId()), response.getPointOfSaleNumber());
        if (projection.get().getOperationNumberCount() == null) {
            return response.defineNumber(0);
        }
        return response.defineNumber(projection.get().getOperationNumberCount());
    }
}
