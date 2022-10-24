package dev.facturador.account.domain.querys;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.LinkedHashMap;

/**
 * Query para el Inicio de session
 */
@Data
public class AccountSignInQuery extends Query<LinkedHashMap<String, String>> {
    //Se utiliza este tipo de valor debido a que la Autenticación por defecto acepta solo
    //valores de tipo APPLICATION_FORM_URLENCODED
    private final MultiValueMap<String, String> keys;

    /**
     * Builder para la Query
     */
    public static class Builder {
        private MultiValueMap<String, String> keys;

        public static AccountSignInQuery.Builder getInstance() {
            return new AccountSignInQuery.Builder();
        }

        public AccountSignInQuery.Builder keys(String username, String password) {
            this.keys = new LinkedMultiValueMap<>();
            this.keys.add("usernameOrEmail", username);
            this.keys.add("password", password);
            return this;
        }


        public AccountSignInQuery build() {
            return new AccountSignInQuery(keys);
        }
    }
}
