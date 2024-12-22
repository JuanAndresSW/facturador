package dev.facturador.operation.fulls.application.handlers;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.operation.core.application.OperationRepository;
import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.operation.fulls.application.FullOperationUtility;
import dev.facturador.operation.fulls.domain.model.FullOperationDisplayed;
import dev.facturador.operation.fulls.domain.querys.GetAnyFullOperationQuery;
import dev.facturador.security.domain.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class GetAnyFullOperationQueryHandler implements PortQueryHandler<FullOperationDisplayed, GetAnyFullOperationQuery> {
    @Autowired
    private final OperationRepository repository;

    @Override
    public FullOperationDisplayed handle(GetAnyFullOperationQuery query) throws Exception {
        Optional<Operation> operation = Optional.empty();
        if(query.getRepository().equals("invoice")){
            operation = repository.findByOperationId(query.getOperationId());
            if (operation.isEmpty() || operation.get().getInvoice() == null) throw new ResourceNotFound("El comerciante no tiene esta factura");
        }

        if(query.getRepository().equals("debit")){
            operation = repository.findByOperationId(query.getOperationId());
            if (operation.isEmpty() || operation.get().getDebitNote() == null) throw new ResourceNotFound("El comerciante no tiene esta nota de debito");
        }
        if(query.getRepository().equals("credit")){
            operation = repository.findByOperationId(query.getOperationId());
            if (operation.isEmpty() || operation.get().getCreditNote() == null) throw new ResourceNotFound("El comerciante no tiene esta nota de credito");
        }

        return toDisplayed(operation.get(), query);
    }


    private FullOperationDisplayed toDisplayed(Operation operation, GetAnyFullOperationQuery query){
        var response = new FullOperationDisplayed();
        if(query.getRepository().equals("invoice")){
            FullOperationUtility.resolveInvoice(operation.getInvoice(), response);
            branchData(operation, response);
        }
        if(query.getRepository().equals("debit")){
            FullOperationUtility.resolveDebitNote(operation.getDebitNote(), response);
            branchData(operation, response);
        }
        if(query.getRepository().equals("credit")){
            FullOperationUtility.resolveCreditNote(operation.getCreditNote(), response);
            branchData(operation, response);
        }
        return response;
    }
    private void branchData(Operation operation, FullOperationDisplayed response){
        var result =operation.getTraderOwner().getBranches().stream()
                .filter(branch -> {
                    var point= branch.getPointsOfSale().stream().filter(
                                    pointOfSale -> pointOfSale.getPointOfSaleNumber() == Integer.parseInt(operation.getIssuingPointOfSaleNumber()))
                            .toList();
                    return !point.isEmpty();
                }).toList().get(0);

        response.setPreferenceColor(result.getPreferenceColor() != null ? result.getPreferenceColor() : null);
        response.setLogo(result.getLogo() != null ? result.getLogo() : null);
    }
}
