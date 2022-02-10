package dev.facturador.controllers;

import dev.facturador.dto.security.CustomUserDetails;
import dev.facturador.dto.security.JwtDto;
import dev.facturador.dto.Message;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.dto.RegisterDto;
import dev.facturador.services.abstracciones.IUserService;
import dev.facturador.services.abstracciones.ISingUpMainAccountService;
import static dev.facturador.util.translator.JSONTranslatorForMainAccount.mainAccountPrepareForSave;

import dev.facturador.jwt.JWTProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Controller para el registro del usuario
 */
@RestController
@RequestMapping(path = "/api/auth")
@Slf4j
public final class SIngUpController {
    @Autowired
    private ISingUpMainAccountService serviceSingUp;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/singup")
    public ResponseEntity<?> singup(@Valid @RequestBody RegisterDto account){
        //Si esta aqui los datos son validos
        //Comprueba que no exista
        if(userService.existsByUsername(account.getUserDto().getUsername())){
            return new ResponseEntity<>(new Message("Username already exists"),HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(account.getUserDto().getEmail())){
            return new ResponseEntity<>(new Message("Email already exists"),HttpStatus.BAD_REQUEST);
        }
        //Registra
        CuentaPrincipal mainAccountRegistered = mainAccountPrepareForSave(account);
        serviceSingUp.register(mainAccountRegistered);
        //Autentica los datos
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mainAccountRegistered.getUserMainAccount().getUsername(), account.getUserDto().getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Token y Response
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetails);
        return new ResponseEntity<>(new JwtDto(token, userDetails.getUsername(), userDetails.getAuthorities()), HttpStatus.CREATED);
    }
}
