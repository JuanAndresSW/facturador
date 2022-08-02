package dev.facturador.branch.application.query;

import dev.facturador.global.application.querys.Query;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;

/**Query para recuperar una version simplificada de la sucursal*/
@Getter
public final class BranchesSummaryQuery extends Query<LinkedList<HashMap<String, Object>>> {
    private final Long traderId;

    public BranchesSummaryQuery(Long traderId) {
        this.traderId = traderId;
    }
    /**
     * Builder del comando
     */
    public static class Builder {
        private Long traderId;

        public static BranchesSummaryQuery.Builder getInstance() {
            return new BranchesSummaryQuery.Builder();
        }

        public BranchesSummaryQuery.Builder traderId(Long traderId) {
            this.traderId = traderId;
            return this;
        }

        public BranchesSummaryQuery build() {
            return new BranchesSummaryQuery(traderId);
        }
    }
}
