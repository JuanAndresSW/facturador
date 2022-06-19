package dev.facturador.mainaccount.application.query.singin;

import dev.facturador.shared.application.querys.Query;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
public class MainAccountSingInQuery extends Query<HttpHeaders> {

    private MultiValueMap<String, String> keys;

    public MainAccountSingInQuery(MultiValueMap<String, String> keys) {
        this.keys = keys;
    }

    public static class Builder {
        private MultiValueMap<String, String> keys;

        public static MainAccountSingInQuery.Builder getInstance() {
            return new MainAccountSingInQuery.Builder();
        }

        public MainAccountSingInQuery.Builder keys(String username, String password) {
            this.keys = new LinkedMultiValueMap<>();
            this.keys.add("usernameOrEmail", username);
            this.keys.add("password", password);
            return this;
        }

        public MainAccountSingInQuery build() {
            return new MainAccountSingInQuery(keys);
        }
    }
}
