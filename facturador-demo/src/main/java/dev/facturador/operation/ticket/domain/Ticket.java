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
    @Column(name = "id_invoice")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @JsonIgnore
    @Column(name = "count_ticket_number", nullable = false, length = 8)
    private Integer operationNumberCount;

    @Column(name = "ticket_number", nullable = false)
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

    public static Ticket create(TicketCommand command){
        var ticket = new Ticket();

        ticket.setOperationNumberCount(command.getRequiredData().getOperationNumberCount());
        ticket.setTicketNumber(command.getRequiredData().getOperationNumber());

        ticket.setOperation(new Operation(
                new Trader(command.getTraderId()),
                command.getRequiredData().getPointOfSaleNumber()));
        //Crear Sender
        ticket.getOperation().setSender(new Sender());
        ticket.getOperation().getSender().setSenderCode(command.getRequiredData().getSenderCuit());
        ticket.getOperation().getSender().setSenderName(command.getRequiredData().getSenderName());
        ticket.getOperation().getSender().setSenderContact(command.getRequiredData().getSenderPhone());
        ticket.getOperation().getSender().setSenderVatCategory(command.getRequiredData().getVatCategory());
        ticket.getOperation().getSender().setSenderAddress(
                command.getRequiredData().getSenderStreet().concat(" ").concat(command.getRequiredData().getSenderAddressNumber()));
        ticket.getOperation().getSender().setOperationSender(ticket.getOperation());
        //Crear productos
        List<Product> lista = new ArrayList<>();

        command.getProducts().forEach(x ->
                lista.add(new Product(x.getQuantity(), x.getPrice(), x.getDetail(), ticket.getOperation())));

        ticket.getOperation().setProducts(lista);


        ticket.getOperation().setIssueDate(LocalDate.now());
        return ticket;
    }
}
