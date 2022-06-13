package dev.facturador.pointofsale.application.subdomain.query;

import dev.facturador.pointofsale.domain.subdomain.ControlOfPointOfSale;
import dev.facturador.shared.application.querys.Query;

public class ControlOfPosGetQuery extends Query<ControlOfPointOfSale> {
    private Long traderID;

    public ControlOfPosGetQuery(Long traderID) {
        this.traderID = traderID;
    }

    public Long getTraderID() {
        return this.traderID;
    }

    public static class Builder {
        private Long traderID;

        public static ControlOfPosGetQuery.Builder getInstance() {
            return new ControlOfPosGetQuery.Builder();
        }

        public ControlOfPosGetQuery.Builder traderID(Long traderID) {
            this.traderID = traderID;
            return this;
        }

        public ControlOfPosGetQuery build() {
            return new ControlOfPosGetQuery(traderID);
        }
    }
}
