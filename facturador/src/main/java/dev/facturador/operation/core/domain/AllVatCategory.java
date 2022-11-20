package dev.facturador.operation.core.domain;

public enum AllVatCategory {
    REGISTERED_RESPONSIBLE("Responsable Inscripto"),
    MONOTAX_RESPONSIBLE("Responsable Monotributista"),
    EXCEMPT("Sujeto Excento"),
    END_CONSUMER("Consumidor Final"),
    OTHER("Otro");

    private final String vatInSpanish;

    AllVatCategory(String vatInSpanish) {
        this.vatInSpanish = vatInSpanish;
    }

    public static AllVatCategory defineAllVat(String vatCategory) {
        if (vatCategory.contains("Ins")) {
            return AllVatCategory.REGISTERED_RESPONSIBLE;
        }
        if (vatCategory.contains("Otro")) {
            return AllVatCategory.OTHER;
        }
        if (vatCategory.contains("Ex")) {
            return AllVatCategory.EXCEMPT;
        }
        if (vatCategory.contains("Cons")) {
            return AllVatCategory.END_CONSUMER;
        }
        return AllVatCategory.MONOTAX_RESPONSIBLE;
    }

    public String toFixedVat() {
        return vatInSpanish;
    }
}
