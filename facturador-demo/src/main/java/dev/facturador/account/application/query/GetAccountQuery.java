package dev.facturador.account.application.query;

import dev.facturador.account.domain.Account;
import dev.facturador.global.application.querys.Query;
import lombok.Getter;

/**Query para recuperar cuenta*/
@Getter
public class GetAccountQuery extends Query<Account> {

    private final String username;

    public GetAccountQuery(String username) {
        this.username = username;
    }
    /**Builder de la cuenta*/
    public static class Builder {
        private String username;

        public static GetAccountQuery.Builder getInstance() {
            return new GetAccountQuery.Builder();
        }

        public GetAccountQuery.Builder username(String username) {
            this.username = username;
            return this;
        }

        public GetAccountQuery build() {
            return new GetAccountQuery(username);
        }
    }

}
