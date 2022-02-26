package dev.facturador.controllers;

import dev.facturador.bo.RegisterBo;
import dev.facturador.dto.ErrorResponse;
import dev.facturador.dto.IApiResponse;
import dev.facturador.dto.RegisterResponse;
import dev.facturador.services.IMainAccountService;
import dev.facturador.util.MainAccountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.util.Date;

import static dev.facturador.entities.CuentaPrincipal.createMainAccountForRegister;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class MainAccountController {
    private static final String REGISTER_MAIN = "/auth/mainaccounts";
    @Autowired
    private IMainAccountService mainAccountService;
    @Autowired
    private MainAccountUtil mainAccountUtil;

    /**
     * Registra la cuenta principal en la base de datos
     * <br/>
     * Redirige al login y recupera de la respuesta el {@code Access Token} y {@code Refresh Token}
     *
     * @param tryRegister {@link RegisterBo} Bussines Object para recibir el JSON
     * @return {@link HttpEntity} con el body de {@link IApiResponse} object
     */
    @PostMapping(REGISTER_MAIN)
    public HttpEntity<? extends IApiResponse> singup(@Valid @RequestBody RegisterBo tryRegister) {
        String message = mainAccountUtil.whenIndicesAreRepeatedReturnErrror(tryRegister, mainAccountService);
        if (StringUtils.hasText(message)) {
            return new ResponseEntity<>(new ErrorResponse(new Date(), message), HttpStatus.BAD_REQUEST);
        }

        var mainAccountLogged = createMainAccountForRegister(tryRegister);
        mainAccountService.register(mainAccountLogged);

        var headers = callFilterLogin(tryRegister.getUserBo().username(), tryRegister.getUserBo().password());
        String accesToken = headers.get("Access-token").get(0);
        String refreshToken = headers.get("Refresh-token").get(0);

        return new ResponseEntity<>(new RegisterResponse(accesToken, refreshToken), HttpStatus.CREATED);
    }

    private HttpHeaders callFilterLogin(String usernameOrEmail, String password) {
        WebClient client = WebClient.builder().baseUrl("http://localhost:8080").build();
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", usernameOrEmail);
        bodyValue.add("password", password);

        return client.post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromFormData(bodyValue))
                .retrieve().toEntity(String.class).block().getHeaders();
    }
}
