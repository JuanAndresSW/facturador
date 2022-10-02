package dev.facturador.operation.wholeoperation.domain.model;

import dev.facturador.operation.shared.domain.model.ProductModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
public final class WholeOperationDisplayed {
    private String sellConditions;
    private int vat;
    private LocalDate issueDate;
    private String type;
    private String operationNumber;

    private List<ProductModel> products;

    private String senderCode;
    private String senderName;
    private String senderAddress;
    private String senderVatCategory;
    private String senderContact;

    private String receiverCode;
    private String receiverName;
    private String receiverAddress;
    private String receiverVatCategory;
    private String receiverPostalCode;
    private String receiverLocality;

    private String preferenceColor;
}
