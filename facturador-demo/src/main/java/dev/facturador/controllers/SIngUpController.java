package dev.facturador.controllers;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.modelo.Account;
import dev.facturador.modelo.Message;
import dev.facturador.services.ISingUpMainAccountService;
import static dev.facturador.util.JSONTranslator.translatorAccountToMainAccount;

import dev.facturador.services.component.CustomBCryptPasswordEncoder;
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
    @Autowired
    private CustomBCryptPasswordEncoder passwordEncoder;


    @PostMapping("/singup")
    public ResponseEntity<?> getDataSingUp(@Valid @RequestBody Account account, BindingResult result){
        CuentaPrincipal mainAccount = translatorAccountToMainAccount(account);
        mainAccount.getMainAccountDetails().setPassword(
                passwordEncoder.encode(mainAccount.getMainAccountDetails().getPassword()));

        if(result.hasErrors()){
            return new ResponseEntity<>(new Message("Not Valid"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mainAccount, HttpStatus.OK);
    }
}