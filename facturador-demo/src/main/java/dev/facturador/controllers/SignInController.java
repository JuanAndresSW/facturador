package dev.facturador.controllers;

import dev.facturador.dto.LoginDto;
import dev.facturador.dto.response.IApiResponse;
import dev.facturador.dto.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

import static dev.facturador.util.SignInUtil.createLoginResponse;
import static dev.facturador.util.SignInUtil.getDataFromHeader;
import static dev.facturador.util.WebClientUtil.buildValueLogin;
import static dev.facturador.util.WebClientUtil.responseHeadersToLogin;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class SignInController {
    @Autowired
    private WebClient webClient; //La inicializacion de esto se encuentra en FacturadorApplication

    /**
     * ---IMPORTANTE---
     * Este metodo es para interceptar el JSON que recibe del Front
     * El metodo de login es otro (Este se en cuentra en paquete - filter . clase - CustomAuthenticationToken )
     * El metodo de login original no acepta JSON solo valores de tipo application/x-www-form-urlencoded
     * Entonces recupero el JSON lo transformo en ese valor
     * Luego se redirige la request hacia ese metodo
     * Y la respuesta la recibo en los headers
     */
    @PostMapping("/login")
    public HttpEntity<? extends IApiResponse> login(@Valid @RequestBody LoginDto tryLogin) {
        //Recupero los header de la respuesta recibida
        var headers = responseHeadersToLogin(buildValueLogin(tryLogin.usernameOrEmail(), tryLogin.password()), webClient);
        var data = getDataFromHeader(headers);
        LoginResponse response = createLoginResponse(headers, data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Comprueba token al iniciar App
     *
     * @return Si llega aqui es que se autorizo
     */
    @PostMapping()
    public HttpEntity<String> authenticateToken() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
