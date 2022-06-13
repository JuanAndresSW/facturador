package dev.facturador.shared.domain.sharedpayload;

public enum Vat {
    REGISTERED_RESPONSIBLE("Responsable Inscripto"),
    MONOTAX_RESPONSIBLE("Responsable Monotributista");

    private String nameVat;

    private Vat(String nameVat) {
        this.nameVat = nameVat;
    }

    public String getNameVat() {
        return nameVat;
    }
}
