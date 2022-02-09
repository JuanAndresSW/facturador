package dev.facturador.controllers;

import dev.facturador.dto.JwtDto;
import dev.facturador.dto.Message;
import dev.facturador.dto.LoginDto;
import dev.facturador.services.abstracciones.IUserService;
import dev.facturador.jwt.JWTProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    /**
     * Hace las comprobaciones necesarias para el login
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto user){
        var userBeforeLoged = service.getUserWithCrdentials(user);
        if(userBeforeLoged == null){
            return new ResponseEntity<>(new Message("Invalid Credentials"), HttpStatus.BAD_REQUEST);
        }
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userBeforeLoged.getUsername(), userBeforeLoged.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token =jwtProvider.generateToekn(String.valueOf(userBeforeLoged.getUserId()), authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new ResponseEntity<>(new JwtDto(token, userDetails.getUsername(), userDetails.getAuthorities()), HttpStatus.OK);
    }
}
