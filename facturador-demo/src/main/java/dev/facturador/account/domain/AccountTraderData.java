package dev.facturador.account.domain;

public record AccountTraderData(String businessName, String vatCategory, String cuit) {

    public static AccountTraderData valueOf(Account account) {
        var trader = account.getOwnerTrader();
        return new AccountTraderData(trader.getBusinessName(), trader.getVat().getNameVat(), trader.getCuit());
    }
}
