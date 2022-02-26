package dev.facturador.controllers;

import dev.facturador.bo.RegisterBo;
import dev.facturador.dto.ErrorResponse;
import dev.facturador.dto.IApiResponse;
import dev.facturador.dto.RegisterResponse;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.services.IMainAccountService;
import dev.facturador.util.MainAccountUtil;
import dev.facturador.util.WebClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private IMainAccountService serviceMainAccount;
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
        String message = mainAccountUtil.whenIndicesAreRepeatedReturnErrror(tryRegister);
        if (StringUtils.hasText(message)) {
            return new ResponseEntity<>(new ErrorResponse(new Date(), message), HttpStatus.BAD_REQUEST);
        }

        var mainAccountLogged = createMainAccountForRegister(tryRegister);
        serviceMainAccount.register(mainAccountLogged);
        WebClientUtil webClientUtil = new WebClientUtil(WebClient.builder().baseUrl("http://localhost:8080").build());
        var headers = webClientUtil.responseHeadersToLogin(webClientUtil.buildValueLogin
                (tryRegister.getUserBo().username(), tryRegister.getUserBo().password()));
        String accesToken = headers.get("Access-token").get(0);
        String refreshToken = headers.get("Refresh-token").get(0);

        return new ResponseEntity<>(new RegisterResponse(accesToken, refreshToken), HttpStatus.CREATED);
    }
}
