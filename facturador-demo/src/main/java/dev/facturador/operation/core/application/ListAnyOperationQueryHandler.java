package dev.facturador.operation.core.application;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.operation.core.domain.DocumentHistory;
import dev.facturador.operation.core.domain.ListAnyOperationQuery;
import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.trader.domain.Trader;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ListAnyOperationQueryHandler implements PortQueryHandler<List<DocumentHistory>, ListAnyOperationQuery> {
    @Autowired
    private final OperationRepository repository;

    @Override
    public List<DocumentHistory> handle(ListAnyOperationQuery query) throws Exception {
        Thread.sleep(2);
        var operations = Flux.fromStream( repository.findByTraderOwner(new Trader(query.getTraderID())).stream() );

        operations =operations
                .filter(event ->{
                    final Map<String, Boolean> match = Map.of(
                            "invoice", event.getInvoice() != null,
                            "debit-note", event.getDebitNote() != null,
                            "credit-note", event.getCreditNote() != null,
                            "remittance", event.getRemittance() != null,
                            "ticket", event.getTicket() != null,
                            "default", true);

                    return match.get(query.getRepository());
                })
                .filter(operation -> {
                    if(query.getBranchId() == 0) return true;
                    final var issuingBranch =operation.getTraderOwner().getBranches().stream()
                            .filter(branch -> branch.getBranchId().equals(query.getBranchId()))
                            .filter(branch -> {
                                var point= branch.getPointsOfSale().stream().filter(
                                                pointOfSale -> pointOfSale.getPointOfSaleNumber().equals(Integer.parseInt(operation.getIssuingPointOfSaleNumber())))
                                        .collect(Collectors.toList());
                                return !point.isEmpty();
                            })
                            .collect(Collectors.toList());

                    return !issuingBranch.isEmpty();
                })
                .filter(operation -> query.getPointOfSaleNumber() != 0 ?
                        Long.parseLong(operation.getIssuingPointOfSaleNumber()) == query.getPointOfSaleNumber() : true);

        return toView(operations);
    }

    private List<DocumentHistory> toView(Flux<Operation> operations){
        List<DocumentHistory> history = new LinkedList<>();
        operations.subscribe(operation -> {
            final var issuingBranch = operation.getTraderOwner().getBranches().stream()
                    .filter(branch -> {
                        var point= branch.getPointsOfSale().stream().filter(
                                        pointOfSale -> pointOfSale.getPointOfSaleNumber() == Integer.parseInt(operation.getIssuingPointOfSaleNumber()))
                                .collect(Collectors.toList());
                        return !point.isEmpty();
                    }).findFirst().get();

            history.add(DocumentHistory.builder()
                    .operationId(operation.getOperationId())
                    .branchId(issuingBranch.getBranchId())
                    .issueDate(operation.getIssueDate().toString())
                    .receiverName(operation.getReceiver() != null ? operation.getReceiver().getReceiverName() : "undefined")
                    .receiverCuit(operation.getReceiver() != null ? operation.getReceiver().getReceiverCode() : "undefined")
                    .build());

            var count = new AtomicLong(history.stream().count());
            if(operation.getInvoice() != null) {
                history.get(count.intValue()-1).setDocumentClass("Factura");
                history.get(count.intValue()-1).setOperationNumber(operation.getInvoice().getInvoiceNumber());
                history.get(count.intValue()-1).setDocumentType(operation.getInvoice().getType().name());
            }
            if(operation.getDebitNote() != null) {
                history.get(count.intValue()-1).setDocumentClass("Nota de débito");
                history.get(count.intValue()-1).setOperationNumber(operation.getDebitNote().getDebitNumber());
                history.get(count.intValue()-1).setDocumentType(operation.getDebitNote().getType().name());
            }
            if(operation.getCreditNote() != null) {
                history.get(count.intValue()-1).setDocumentClass("Nota de crédito");
                history.get(count.intValue()-1).setOperationNumber(operation.getCreditNote().getCreditNumber());
                history.get(count.intValue()-1).setDocumentType(operation.getCreditNote().getType().name());
            }
            if(operation.getTicket() != null) {
                history.get(count.intValue()-1).setDocumentClass("Ticket");
                history.get(count.intValue()-1).setOperationNumber(operation.getTicket().getTicketNumber());
                history.get(count.intValue()-1).setDocumentType("undefined");
            }
        });
        return history;
    }

}
