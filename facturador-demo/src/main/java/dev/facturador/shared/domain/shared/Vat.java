package dev.facturador.shared.domain.shared;

public enum Vat {
    RESPONSABLE_INSCRIPTO("Responsable Inscripto"),
    MONOTRIBUTISTA("Monotributista"),
    SUJETO_EXENTO("Sujeto Exento");

    private String nameVat;

    private Vat(String nameVat) {
        this.nameVat = nameVat;
    }

    public String getNameVat() {
        return nameVat;
    }
}
