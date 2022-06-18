package dev.facturador.global.domain.sharedpayload;

public enum Vat {
    REGISTERED_RESPONSIBLE("Responsable Inscripto"),
    MONOTAX_RESPONSIBLE("Responsable Monotributista");

    private final String nameVat;

    Vat(String nameVat) {
        this.nameVat = nameVat;
    }

    public String getNameVat() {
        return nameVat;
    }
}
