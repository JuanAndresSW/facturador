package dev.facturador.controllers;

import dev.facturador.dto.ErrorDetailsDto;
import dev.facturador.dto.LoginDto;
import dev.facturador.dto.security.CustomUserDetails;
import dev.facturador.dto.security.ApiResponse;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.dto.RegisterDto;
import dev.facturador.services.IUserService;
import dev.facturador.services.ISingUpMainAccountService;
import static dev.facturador.util.JSONTranslatorForMainAccount.mainAccountPrepareForSave;

import dev.facturador.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


/**
 * Controller para la autenticacion
 */
@RestController
@RequestMapping(path = "/api/auth")
public final class AuthController {
    @Autowired
    private ISingUpMainAccountService serviceSingUp;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public HttpEntity<?> signup(@Valid @RequestBody RegisterDto account){
        //Si esta aqui los datos son validos
        //Comprueba que no exista
        if(userService.existsByUsername(account.getUserDto().getUsername())){
            return new ResponseEntity<>(new ErrorDetailsDto(new Date(), "Username already exist", "This username is already registered in the database"),HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(account.getUserDto().getEmail())){
            return new ResponseEntity<>(new ErrorDetailsDto(new Date(), "Email already exist", "This email is already registered in the database"),HttpStatus.BAD_REQUEST);
        }
        //Registra
        CuentaPrincipal mainAccountRegistered = mainAccountPrepareForSave(account);
        serviceSignUp.register(mainAccountRegistered);
        //Autentica los datos
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mainAccountRegistered.getUserMainAccount().getUsername(), account.getUserDto().getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Token y Response
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetails);
        return new ResponseEntity<>(new ApiResponse(token), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto user){
        //Compruebo que las credenciales sean correctas
        //Traigo un usuario porque no se si inicio con Email o Username
        var userBeforeLoged = userService.getUserWithCrdentials(user);
        if(userBeforeLoged == null){
            return new ResponseEntity<>(new ErrorDetailsDto(new Date(), "Invalid Credentials", "Credentials do not exist in the database"), HttpStatus.BAD_REQUEST);
        }
        //Se crea la autenticacion para Spring
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userBeforeLoged.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Token y response
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token =jwtProvider.generateToken(userDetails);

        return new ResponseEntity<>(new ApiResponse(token), HttpStatus.OK);
    }
}
