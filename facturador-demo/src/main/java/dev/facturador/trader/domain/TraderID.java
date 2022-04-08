package dev.facturador.trader.domain;

public record TraderID(Long IDTrader) {

    public static TraderID valueOf(Long IDTrader){
        return new TraderID(IDTrader);
    }
}
