package dev.facturador.controllers;

import dev.facturador.dto.ErrorDetailsDto;
import dev.facturador.dto.LoginDto;
import dev.facturador.dto.security.CustomUserDetails;
import dev.facturador.dto.security.ApiResponse;
import dev.facturador.dto.RegisterDto;
import dev.facturador.services.IMainAccountService;
import dev.facturador.services.ITraderService;
import dev.facturador.services.IUserService;
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

@RestController
@RequestMapping(path = "/api/auth")
public final class AuthController {
    @Autowired
    private IMainAccountService serviceSingUp;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ITraderService serviceTrader;

    /**
     * Registra la cuenta principal
     * @param account RegisterDto es el dto para el registro contiene usuario y comerciante
     * @return Token de inicio de sesio / Error con codigo 400
     */
    @PostMapping("/main/signup")
    public HttpEntity<? extends ApiResponse> singup(@Valid @RequestBody RegisterDto account){
        if(userService.existsByUsername(account.getUserDto().getUsername())){
            return  new ResponseEntity<>(new ErrorDetailsDto(new Date(), "Nombre de usuario ya se encuentra en uso", "Este nombre de usuario ya esta registrado en la base de datos"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(account.getUserDto().getEmail())){
            return new ResponseEntity<>(new ErrorDetailsDto(new Date(), "Email ya se encuentra en uso", "Este Email ya esta registrado en la base de datos"),HttpStatus.BAD_REQUEST);
        }
        if(serviceTrader.existsByCode(account.getTraderDto().getCode())){
            return new ResponseEntity<>(new ErrorDetailsDto(new Date(), "Cuit/Cuil ya se encuentra en uso", "El cuit/cuil ya esta registrado en la base de datos"),HttpStatus.BAD_REQUEST);
        }
        //Registra
        var mainAccountRegistered = mainAccountPrepareForSave(account);
        serviceSingUp.register(mainAccountRegistered);
        //Autentica los datos
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mainAccountRegistered.getUserMainAccount().getUsername(), account.getUserDto().getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Token y Response
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetails);
        return new ResponseEntity<>(new ApiResponse(token), HttpStatus.CREATED);
    }

    /**
     * Inicia sesion con un username o email enviado en caso q
     * @param user Dto recibido para el inicio de sesion
     * @return retorna el token
     */
    @PostMapping("/login")
    public HttpEntity<? extends ApiResponse> login(@Valid @RequestBody LoginDto user){
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
