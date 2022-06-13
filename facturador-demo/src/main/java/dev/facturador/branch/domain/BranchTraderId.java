package dev.facturador.branch.domain;

public record BranchTraderId(Long IDTrader) {
    public static BranchTraderId valueOf(Long IDTrader) {
        return new BranchTraderId(IDTrader);
    }
}
