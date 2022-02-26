package dev.facturador.controllers;

import dev.facturador.entities.AvatarUsuario;
import dev.facturador.services.IUserAvatarService;
import dev.facturador.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

    @Autowired
    private IUserAvatarService serviceUserAvatar;

    @PostMapping(GET_AVATAR_IN_LOGIN)
    public ResponseEntity<String> getUserAvatar(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        var jwt = new JWTUtil();
        String username = "";
        if(jwt.verifyAuthToken(authHeader)){
            String token = authHeader.substring("Bearer ".length());
            var decoded = jwt.createDecoder(token);
            username = jwt.getSubject(decoded);
        }
        var userAvatar = serviceUserAvatar.getAvatarUsuarioByUsername(username);
        if(userAvatar != null){
            return ResponseEntity.ok().body(userAvatar.getAvatar());
        }
        return ResponseEntity.badRequest().body("Este usuario no tiene avatar");
    }
}
