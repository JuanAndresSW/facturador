package dev.facturador.operation.fulls.domain.model;

public enum SellConditions {
    CASH("Al contado"),
    CARD("Tarjeta"),
    CHECKING_ACCOUNT("Cuenta corriente"),
    DOCUMENT("Documento"),
    OTHER("otro");

    private final String condition;

    SellConditions(String condition) {
        this.condition = condition;
    }

    public static SellConditions defineSellCondition(String condition) {
        if (condition.equals("Tar")) {
            return SellConditions.CARD;
        }
        if (condition.equals("Doc")) {
            return SellConditions.DOCUMENT;
        }
        if (condition.equals("Al")) {
            return SellConditions.CASH;
        }
        if (condition.equals("ot")) {
            return SellConditions.OTHER;
        }
        return SellConditions.CHECKING_ACCOUNT;
    }

    public String getCondition() {
        return condition;
    }
}

/*
Que es un punto de acceso OAP
Cuales son sus caracteristicas
Buscar en internet distintos modelos de puntos de acceso para wifi
Haz una tabla en la que compares prestaciones como:
    la velocidad, la potencia, los puertos Ethernet que incorpora y el precio que incorpora

 */
