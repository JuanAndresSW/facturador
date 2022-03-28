package dev.facturador.user.infrastructure.resources;

import dev.facturador.shared.infrastructure.JWT;
import dev.facturador.user.application.JWTOfUser;
import dev.facturador.user.infrastructure.IUserAvatarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class GetAvatarController {
    @Autowired
    private IUserAvatarService serviceUserAvatar;

    @GetMapping("/useravatars")
    public ResponseEntity<String> getUserAvatar(HttpServletRequest request) throws Exception {
        String authHeader = request.getHeader(AUTHORIZATION);
        JWT<String> jwt = new JWTOfUser();
        var userAvatar = serviceUserAvatar.getAvatarUserByEmail(jwt.createUserByToken(authHeader));

        if (userAvatar != null) {
            return ResponseEntity.ok().body(userAvatar.getAvatar());
        }
        return ResponseEntity.badRequest().body("Este usuario no tiene Avatar");
    }
}
