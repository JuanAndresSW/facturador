package dev.facturador.operation.ticket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.operation.core.domain.entity.Product;
import dev.facturador.operation.core.domain.entity.Sender;
import dev.facturador.trader.domain.Trader;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dev.facturador.operation.core.domain.AllVatCategory.defineAllVat;

@Entity
@Table(name = "ticket")
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public final class Ticket implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_ticket")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(name = "count_ticket_number", nullable = false, length = 8)
    private Integer operationNumberCount;

    @Column(name = "ticket_number", nullable = false, length = 13)
    private String ticketNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_operation_parent", nullable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operation;

    public Ticket(Operation operation) {
        this.operation = operation;
    }


    public Ticket(Long ticketId) {
        this.ticketId = ticketId;
    }

    public static Ticket create(TicketCommand command, Clock clock){
        var ticket = new Ticket();

        ticket.setOperationNumberCount(command.getRequiredData().getOperationNumberCount());
        ticket.setTicketNumber(command.getRequiredData().getOperationNumber());

        var operation = new Operation(
                new Trader(command.getTraderId()), command.getRequiredData().getPointOfSaleNumber());

        //Crear Sender
        operation.setSender(new Sender());
        operation.getSender().setSenderCode(command.getRequiredData().getSenderCuit());
        operation.getSender().setSenderName(command.getRequiredData().getSenderName());
        operation.getSender().setSenderContact(command.getRequiredData().getSenderPhone());
        operation.getSender().setSenderVatCategory(command.getRequiredData().getVatCategory());
        operation.getSender().setSenderAddress(
                command.getRequiredData().getSenderStreet().concat(" ").concat(command.getRequiredData().getSenderAddressNumber()));
        operation.getSender().setOperationSender(operation);
        //Crear productos
        List<Product> lista = new ArrayList<>();

        command.getProducts().forEach(x ->
                lista.add(new Product(x.getQuantity(), x.getPrice(), x.getDetail(), operation)));

        operation.setProducts(lista);

        operation.setIssueDate(LocalDate.now(clock));

        ticket.setOperation(operation);
        return ticket;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", operationNumberCount=" + operationNumberCount +
                ", ticketNumber='" + ticketNumber + '\'' +
                '}';
    }
}
