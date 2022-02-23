package dev.facturador.controllers;

import dev.facturador.dto.RegisterDto;
import dev.facturador.dto.response.ErrorResponse;
import dev.facturador.dto.response.IApiResponse;
import dev.facturador.dto.response.RegisterResponse;
import dev.facturador.services.IMainAccountService;
import dev.facturador.services.ITraderService;
import dev.facturador.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.util.Date;

import static dev.facturador.util.SignUtil.whetherExistsReturnMessage;
import static dev.facturador.util.TranslatorForMainAccount.mainAccountPrepareForSave;
import static dev.facturador.util.WebClientUtil.buildValueLogin;
import static dev.facturador.util.WebClientUtil.responseHeadersToLogin;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class RegisterController {
    @Autowired
    private IMainAccountService serviceMainAccount;
    @Autowired
    private WebClient client; //La inicializacion de esto se encuentra en FacturadorApplication

    @Autowired
    private IUserService userService;
    @Autowired
    private ITraderService traderService;

    /**
     * Registra la cuenta principal valida
     * Redirige el flujo de la request al login, recibe la respuesta de este
     * @param account RegisterDto es el dto para el registro contiene usuario y comerciante
     * @return Retorna el Acces-Token y el Refresh-Token
     */
    @PostMapping("/main/signup")
    public HttpEntity<? extends IApiResponse> singup(@Valid @RequestBody RegisterDto account) {
        //Trae un mensage si es que hay datos repetidos
        String message = whetherExistsReturnMessage(account, userService, traderService);
        if(StringUtils.hasText(message)){
            return  new ResponseEntity<>(new ErrorResponse(new Date(), message), HttpStatus.BAD_REQUEST);
        }

        var mainAccountRegistered = mainAccountPrepareForSave(account);
        serviceMainAccount.register(mainAccountRegistered);

        //Recupero los header de la respuesta recibida
        var headers = responseHeadersToLogin
                (buildValueLogin(account.getUserDto().username(), account.getUserDto().password()), client);

        String accesToken = headers.get("Access-token").get(0);
        String refresToken = headers.get("Refresh-token").get(0);

        return new ResponseEntity<>(new RegisterResponse(accesToken, refresToken), HttpStatus.CREATED);
    }

    /**
     * Registrar Cuenta Secundaria
     * No hay implementacion. Solo se sabe que solo usuario MAIN pueden hacer esto
     * @return
     */
    @PostMapping("/secondaryAccounts")
    @PreAuthorize("MAIN")
    public HttpEntity<? extends IApiResponse> registerSecondary(){
        //No necesita implementacion por ahora
        return null;
    }
}
