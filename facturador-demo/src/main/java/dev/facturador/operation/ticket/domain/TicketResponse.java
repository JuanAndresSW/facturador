package dev.facturador.operation.ticket.domain;

import dev.facturador.operation.core.domain.model.ProductModel;
import lombok.Data;

import java.util.List;

@Data
public final class TicketResponse {

    private String senderName;
    private String senderCuit;
    private String senderVatCategory;
    private String senderAddress;
    private String ticketNumber;
    private String issueDate;
    private List<ProductModel> products;

}
