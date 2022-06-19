package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.mainaccount.application.query.singin.MainAccountSingInQuery;
import dev.facturador.mainaccount.domain.MainAccountSingInRequest;
import dev.facturador.mainaccount.domain.MainAccountSingInResponse;
import dev.facturador.shared.application.querys.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth/mainaccounts")
public class SingInMainAccountResource {

    private QueryBus queryBus;

    public SingInMainAccountResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    /**
     * Este metodo realiza el trabajo de inicio de sesion sin la base del Registro
     *
     * @param keyRequest Es {@link MainAccountSingInRequest}, este contiene las credenciales de inicio de sesion
     * @return {@link ResponseEntity} con el body de {@link MainAccountSingInResponse}
     */
    @PostMapping("/log-in")
    public HttpEntity loginWithJSON(@Valid @RequestBody MainAccountSingInRequest keyRequest) throws Exception {

        MainAccountSingInQuery query = MainAccountSingInQuery.Builder.getInstance()
                .keys(keyRequest.usernameOrEmail(), keyRequest.password()).build();

        var headers = queryBus.handle(query);

        var response = this.createLoginResponse(headers);

        return ResponseEntity.ok().body(response);
    }

    private MainAccountSingInResponse createLoginResponse(HttpHeaders headers) {
        String active = headers.get("active").get(0);
        String passive = headers.get("pasive").get(0);
        String IDTrader = headers.get("IDTrader").get(0);
        String username = headers.get("username").get(0);
        String role = headers.get("role").get(0);
        return new MainAccountSingInResponse(username, parseLong(IDTrader), role, parseInt(active), parseInt(passive),
                headers.get("accessToken").get(0), headers.get("refreshToken").get(0));
    }
}
