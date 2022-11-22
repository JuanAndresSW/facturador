package dev.facturador.operation.core.application;

import dev.facturador.global.domain.VatCategory;
import dev.facturador.operation.core.domain.AllVatCategory;
import dev.facturador.operation.core.domain.DocumentType;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class OperationService {

    public static final Function<String, AllVatCategory> receiverCategory = AllVatCategory::defineAllVat;
    public static final Function<String, VatCategory> senderCategory = VatCategory::defineVat;
    public static final BiFunction<String, String, DocumentType> types = (sender, receiver) ->
            DocumentType.defineType(senderCategory.apply(sender), receiverCategory.apply(receiver));

    public String formatOperationNumber(Integer pointOfSaleNumber, Integer operationNumber) {
        var builder = new StringBuilder();
        if (pointOfSaleNumber.toString().length() == 4 && operationNumber.toString().length() == 8) {
            builder.append(pointOfSaleNumber).append("-").append(operationNumber);
        } else {
            builder.append("0".repeat(4 - pointOfSaleNumber.toString().length())).append(pointOfSaleNumber);
            builder.append("-");
            builder.append("0".repeat(8 - operationNumber.toString().length())).append(operationNumber);
        }

        return builder.toString();
    }
}
