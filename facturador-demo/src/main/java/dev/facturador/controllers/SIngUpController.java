package dev.facturador.controllers;

import com.sun.jdi.request.InvalidRequestStateException;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import dev.facturador.dto.Message;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.dto.Account;
import dev.facturador.services.ISingUpMainAccountService;
import static dev.facturador.util.JSONTranslator.translatorAccountToMainAccount;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Controller para el registro del usuario
 */
@RestController
//RequestMapping para no tener que repetir /api en todos lados
@RequestMapping(path = "/api")
public class SIngUpController {
    @Autowired
    private ISingUpMainAccountService service;

    @PostMapping("/singup")
    public ResponseEntity<?> getDataSingUp(@Valid @RequestBody Account account,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Error in create user", bindingResult);
        }
        CuentaPrincipal mainAccount = translatorAccountToMainAccount(account);
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        mainAccount.getMainAccountDetails().setPassword(argon.hash(2, 1024, 2, mainAccount.getMainAccountDetails().getPassword().getBytes()));

        return new ResponseEntity<>(mainAccount, HttpStatus.OK);
    }
}
