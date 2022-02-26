package dev.facturador.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public final class UserAvatarController {
    private static final String GET_AVATAR_IN_LOGIN = "/useravatars";

    @PostMapping(GET_AVATAR_IN_LOGIN)
    public ResponseEntity<String> getUserAvatar(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        return ResponseEntity.ok().body("asd");
    }
}
