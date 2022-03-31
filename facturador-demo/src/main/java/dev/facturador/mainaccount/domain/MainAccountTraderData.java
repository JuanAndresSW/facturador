package dev.facturador.mainaccount.domain;

public record MainAccountTraderData(String businessName, String vatCategory, String auniqueKey) {

    public static MainAccountTraderData starter(MainAccount account) {
        var trader = account.getAccountOwner();
        return new MainAccountTraderData(trader.getName(), trader.getVat().getNameVat(), trader.getUniqueKey());
    }
}
