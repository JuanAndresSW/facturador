package dev.facturador.controllers;

import dev.facturador.jwt.JWTProvider;
import dev.facturador.dto.security.CustomUserDetails;
import dev.facturador.dto.security.JwtDto;
import dev.facturador.dto.Message;
import dev.facturador.dto.LoginDto;
import dev.facturador.services.abstracciones.IUserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * RestController para el login
 */
@RestController
@RequestMapping(path = "/api/auth")
@Slf4j
public final class LoginController {
    @Autowired
    private IUserService service;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto user){
        //Compruebo que las credenciales sean correctas
        //Traigo un usuario porque no se si inicio con Email o Username
        var userBeforeLoged = service.getUserWithCrdentials(user);
        if(userBeforeLoged == null){
            return new ResponseEntity<>(new Message("Invalid Credentials"), HttpStatus.BAD_REQUEST);
        }
        //Se crea la autenticacion para Spring
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userBeforeLoged.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Token y response
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token =jwtProvider.generateToken(userDetails);

        return new ResponseEntity<>(new JwtDto(token, userDetails.getUsername(), userDetails.getAuthorities()), HttpStatus.OK);
    }
}
