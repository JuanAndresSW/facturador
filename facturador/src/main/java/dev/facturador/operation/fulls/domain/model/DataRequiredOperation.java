package dev.facturador.operation.fulls.domain.model;

import dev.facturador.global.domain.VatCategory;
import dev.facturador.operation.core.application.OperationService;
import dev.facturador.operation.core.domain.DocumentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@NoArgsConstructor
@Data
public final class DataRequiredOperation {
    private DocumentType type;
    private String operationNumber;
    private Integer operationNumberCount;
    private String pointOfSaleNumber;
    private String senderName;
    private String senderStreet;
    private String senderAddressNumber;
    private String senderPhone;
    private String senderCuit;
    private String senderVatCategory;

    public static DataRequiredOperation valueOf(Map<String, Object> values) {
        var response = new DataRequiredOperation();
        response.setSenderCuit(String.valueOf(values.get("cuit")));
        response.setSenderPhone(String.valueOf(values.get("phone")));
        response.setSenderAddressNumber(String.valueOf(values.get("addressNumber")));
        response.setSenderStreet(String.valueOf(values.get("street")));
        response.setSenderName(String.valueOf(values.get("fantasyName")));
        response.setPointOfSaleNumber(String.valueOf(values.get("pointOfSaleNumber")));
        response.setSenderVatCategory(String.valueOf((values.get("vatCategory"))));
        return response;
    }

    public void resolveType(String receiverCategory) {
        this.setType((new OperationService()).types.apply(this.senderVatCategory, receiverCategory));
    }

    public DataRequiredOperation defineNumber(Integer number) {
        log.info("NUmber is {}",number);
        log.info("point number {} ", this.pointOfSaleNumber);

        this.setOperationNumberCount(number + 1);
        log.info("op number count {}", this.operationNumberCount);
        log.info("Pase el 1+0");
        this.setOperationNumber((new OperationService()).formatOperationNumber(Integer.parseInt(this.pointOfSaleNumber), this.operationNumberCount));
        return this;
    }

    public VatCategory getVatCategory() {
        return (new OperationService()).senderCategory.apply(this.senderVatCategory);
    }

}
