package dev.facturador.account.domain;

public record RequiredTraderData(String businessName, String vatCategory, String cuit) {

    public static RequiredTraderData valueOf(Account account) {
        var trader = account.getOwnerTrader();
        return new RequiredTraderData(trader.getBusinessName(), trader.getVatCategory().vatToLowercaseAndSpanish(), trader.getCuit());
    }
}
