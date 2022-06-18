package dev.facturador.account.application.query.get;

import dev.facturador.account.domain.Account;
import dev.facturador.global.application.querys.Query;
import lombok.Getter;

@Getter
public class AccountGetQuery extends Query<Account> {

    private final String username;

    public AccountGetQuery(String username) {
        this.username = username;
    }

    public static class Builder {
        private String username;

        public static AccountGetQuery.Builder getInstance() {
            return new AccountGetQuery.Builder();
        }

        public AccountGetQuery.Builder username(String username) {
            this.username = username;
            return this;
        }

        public AccountGetQuery build() {
            return new AccountGetQuery(username);
        }
    }

}
