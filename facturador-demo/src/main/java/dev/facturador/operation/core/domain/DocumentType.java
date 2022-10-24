package dev.facturador.operation.core.domain;

import dev.facturador.global.domain.VatCategory;

public enum DocumentType {
    A, B, C;

    public static DocumentType defineType(VatCategory senderCategory, AllVatCategory receiverCategory) {
        if (senderCategory.equals(VatCategory.REGISTERED_RESPONSIBLE) &&
                (receiverCategory.equals(AllVatCategory.REGISTERED_RESPONSIBLE) || receiverCategory.equals(AllVatCategory.MONOTAX_RESPONSIBLE))) {
            return DocumentType.A;
        }
        if (senderCategory.equals(VatCategory.REGISTERED_RESPONSIBLE) &&
                (receiverCategory.equals(AllVatCategory.END_CONSUMER) || receiverCategory.equals(AllVatCategory.EXCEMPT))) {
            return DocumentType.B;
        }

        return DocumentType.C;
    }
}
