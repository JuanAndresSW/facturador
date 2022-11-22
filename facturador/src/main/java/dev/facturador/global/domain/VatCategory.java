package dev.facturador.global.domain;

public enum VatCategory {
    REGISTERED_RESPONSIBLE("Responsable Inscripto"),
    MONOTAX_RESPONSIBLE("Responsable Monotributista");

    private final String vatToLowerSpanish;

    VatCategory(String vatToLowerSpanish) {
        this.vatToLowerSpanish = vatToLowerSpanish;
    }

    public static VatCategory defineVat(String vat) {
        if (vat.contains("Ins")) {
            return VatCategory.REGISTERED_RESPONSIBLE;
        }
        return VatCategory.MONOTAX_RESPONSIBLE;
    }

    public String vatToLowercaseAndSpanish() {
        return vatToLowerSpanish;
    }
}
