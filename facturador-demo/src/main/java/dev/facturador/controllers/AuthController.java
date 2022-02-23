package dev.facturador.controllers;

import dev.facturador.dto.response.*;
import dev.facturador.dto.LoginDto;

import static dev.facturador.util.SignUtil.createLoginResponse;
import static dev.facturador.util.SignUtil.getDataFromHeader;
import static dev.facturador.util.WebClientUtil.*;
import static dev.facturador.util.WebClientUtil.buildValueLogin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    @Autowired
    private WebClient client; //La inicializacion de esto se encuentra en FacturadorApplication

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
    public HttpEntity<? extends IApiResponse> login(@Valid @RequestBody LoginDto user) {
        //Recupero los header de la respuesta recibida
        var headers = responseHeadersToLogin(buildValueLogin(user.usernameOrEmail(), user.password()), client);
        var data = getDataFromHeader(headers);
        LoginResponse response = createLoginResponse(headers, data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Comprueba token al iniciar App
     * @return Si llega aqui es que se autorizo
     */
    @PostMapping()
    public HttpEntity<String> authenticateToken(){
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
