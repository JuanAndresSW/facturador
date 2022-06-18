package dev.facturador.account.application.query.signin;

import dev.facturador.global.application.querys.Query;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
public class AccountSingInQuery extends Query<HttpHeaders> {

    private final MultiValueMap<String, String> keys;

    public AccountSingInQuery(MultiValueMap<String, String> keys) {
        this.keys = keys;
    }

    public static class Builder {
        private MultiValueMap<String, String> keys;

        public static AccountSingInQuery.Builder getInstance() {
            return new AccountSingInQuery.Builder();
        }

        public AccountSingInQuery.Builder keys(String username, String password) {
            this.keys = new LinkedMultiValueMap<>();
            this.keys.add("usernameOrEmail", username);
            this.keys.add("password", password);
            return this;
        }

        public AccountSingInQuery build() {
            return new AccountSingInQuery(keys);
        }
    }
}
