package dev.facturador.operation.ticket.application;

import dev.facturador.global.domain.ClockProvider;
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
    @Autowired
    private final ClockProvider clock;

    @Override
    public void handle(TicketCommand command) throws Exception {
        var ticket = Ticket.create(command, clock.clock());
        var operation = operationRepository.save(ticket.getOperation());
        ticket.setOperation(operation);
         ticketRepository.save(ticket);

        command.setId(operation.getOperationId());
    }
}
