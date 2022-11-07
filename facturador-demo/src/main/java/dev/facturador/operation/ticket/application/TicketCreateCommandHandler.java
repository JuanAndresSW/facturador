package dev.facturador.operation.ticket.application;

import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import dev.facturador.operation.core.application.OperationRepository;
import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
import dev.facturador.operation.ticket.domain.Ticket;
import dev.facturador.operation.ticket.domain.TicketCommand;
import dev.facturador.trader.domain.Trader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class TicketCreateCommandHandler implements PortCommandHandler<TicketCommand> {
    @Autowired
    private final TicketRepository ticketRepository;
    @Autowired
    private final OperationRepository operationRepository;

    @Override
    public void handle(TicketCommand command) throws Exception {
        defineNumberTicket(command);
        log.info("Pase el numero de ticket");
        var ticket = Ticket.create(command);
        log.info("CREE EL TICket");
        var operation = operationRepository.save(ticket.getOperation());
        log.info("CREE LA OP");
        ticket.setOperation(operation);
        ticketRepository.save(ticket);

        command.setId(operation.getOperationId());
    }

    private void defineNumberTicket(TicketCommand command){
        var projection = ticketRepository.findOperationNumberCount(new Trader(command.getTraderId()), command.getRequiredData().getPointOfSaleNumber());

        log.info("Projection is: {}", projection);
        if(Objects.isNull(projection.get().getOperationNumberCount()) || projection.get().getOperationNumberCount() == null || projection.isEmpty()) {
            log.info("ENtro bien");
            command.getRequiredData().defineNumber(0);
        }
        log.info("NO entro bien");
        command.getRequiredData().defineNumber(projection.get().getOperationNumberCount());
    }
}
