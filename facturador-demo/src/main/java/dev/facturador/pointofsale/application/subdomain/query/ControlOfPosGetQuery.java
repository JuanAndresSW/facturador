package dev.facturador.pointofsale.application.subdomain.query;

import dev.facturador.global.application.querys.Query;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;

public class ControlOfPosGetQuery extends Query<PointsOfSaleControl> {
    private final Long traderID;

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
