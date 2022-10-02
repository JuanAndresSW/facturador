package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.domain.AccountSignInRestModel;
import dev.facturador.account.domain.querys.AccountSingInQuery;
import dev.facturador.global.domain.abstractcomponents.query.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * EndPoint para iniciar session
 */
@RestController
@RequestMapping(path = "/api/auth/accounts")
public class SignInAccountResource {
    private final QueryBus queryBus;

    @Autowired
    public SignInAccountResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    /**
     * Permite realizar un inicio de sesion en caso que la haya cerrado o expirado
     *
     * @param accountRestModel Es {@link AccountSignInRestModel}, contiene las credenciales de inicio de session
     * @return Datos necesarios para realizar operaciones una vez iniciada la sesion
     */
    @PostMapping("/log-in")
    public HttpEntity<LinkedHashMap<String, String>> loginWithJSON(@Valid @RequestBody AccountSignInRestModel accountRestModel) throws Exception {
        var query = AccountSingInQuery.Builder.getInstance()
                .keys(accountRestModel.usernameOrEmail(), accountRestModel.password()).build();
        var headers = queryBus.handle(query);

        var response = this.createLoginResponse(headers);
        return ResponseEntity.ok().body(response);
    }

    private LinkedHashMap<String, String> createLoginResponse(HttpHeaders headers) {
        String IDTrader = headers.get("IDTrader").get(0);
        String username = headers.get("username").get(0);
        return new LinkedHashMap<String, String>(
                Map.of("username", username,
                        "IDTrader", IDTrader,
                        "accessToken", headers.get("accessToken").get(0),
                        "refreshToken", headers.get("refreshToken").get(0)));
    }
}
