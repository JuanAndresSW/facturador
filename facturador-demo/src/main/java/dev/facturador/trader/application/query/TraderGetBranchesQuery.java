package dev.facturador.trader.application.query;

import dev.facturador.shared.application.querybus.Query;
import dev.facturador.trader.domain.Trader;
import dev.facturador.trader.domain.TraderBranchCollection;
import dev.facturador.trader.domain.TraderID;

public class TraderGetBranchesQuery extends Query<TraderBranchCollection> {
    private TraderID traderID;

    public TraderGetBranchesQuery(TraderID traderID) {
        this.traderID = traderID;
    }

    public TraderID getTraderID() {
        return this.traderID;
    }

    public static class Builder {
        private TraderID traderID;

        public static TraderGetBranchesQuery.Builder getInstance() {
            return new TraderGetBranchesQuery.Builder();
        }

        public TraderGetBranchesQuery.Builder traderID(TraderID traderID) {
            this.traderID = traderID;
            return this;
        }

        public TraderGetBranchesQuery build() {
            return new TraderGetBranchesQuery(traderID);
        }
    }
}
