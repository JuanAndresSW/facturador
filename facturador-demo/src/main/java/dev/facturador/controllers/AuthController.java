package dev.facturador.controllers;

import dev.facturador.dto.ErrorDetailsDto;
import dev.facturador.dto.LoginDto;
import dev.facturador.dto.security.ApiResponse;
import dev.facturador.dto.RegisterDto;
import dev.facturador.services.IMainAccountService;
import dev.facturador.services.ITraderService;
import dev.facturador.services.IUserService;
import static dev.facturador.util.JSONTranslatorForMainAccount.mainAccountPrepareForSave;

import dev.facturador.jwt.JWTUtil;
import dev.facturador.util.Comprobaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(path = "/api/auth")
public final class AuthController {
    @Autowired
    private IMainAccountService serviceSingUp;
    @Autowired
    private JWTUtil jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserService userService;

    /**
     * Registra la cuenta principal
     * @param account RegisterDto es el dto para el registro contiene usuario y comerciante
     * @return Token de inicio de sesio / Error con codigo 400
     */
    @PostMapping("/main/signup")
    public HttpEntity<? extends ApiResponse> singup(@Valid @RequestBody RegisterDto account){
        String message = new Comprobaciones().dataExist(account);
        if(StringUtils.hasText(message)){
            return  new ResponseEntity<>(new ErrorDetailsDto(new Date(), message), HttpStatus.BAD_REQUEST);
        }

        //Registra
        var mainAccountRegistered = mainAccountPrepareForSave(account);
        serviceSingUp.register(mainAccountRegistered);
        //Autentica los datos
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mainAccountRegistered.getUserMainAccount().getUsername(), account.getUserDto().getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + jwt.getTokenValue());

        return new ResponseEntity<>(new ApiResponse(jwt.getTokenValue()), header, HttpStatus.CREATED);
    }

    /**
     * Inicia sesion con un username o email enviado en caso q
     * @param user Dto recibido para el inicio de sesion
     * @return retorna el token
     */
    @PostMapping("/signin")
    public HttpEntity<? extends ApiResponse> login(@Valid @RequestBody LoginDto user){
        var userBeforeLoged = userService.getUserWithCrdentials(user);
        if(userBeforeLoged.isEmpty()){
            return new ResponseEntity<>(new ErrorDetailsDto(new Date(), "Nombre de Usuario o Email no son incorrecto"), HttpStatus.BAD_REQUEST);
        }
        //Se crea la autenticacion para Spring
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userBeforeLoged.get().getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + jwt.getTokenValue());

        return new ResponseEntity<>(new ApiResponse(jwt.getTokenValue()), header, HttpStatus.OK);
    }

    /**
     * Recibe un token y comprueba que sea valido y que no este expirado
     */
    @PostMapping("/")
    public HttpEntity<? extends ApiResponse> authenticateToken(@AuthenticationPrincipal Jwt jwt){

        return new ResponseEntity<>(new ApiResponse("Success, "+ jwt.getSubject()), HttpStatus.OK);
    }


    @PostMapping("/secondary/signup")
    public HttpEntity<? extends ApiResponse> registerSecondary(@RequestParam String token){
        //No necesita implementacion por ahora
        return null;
    }

}
