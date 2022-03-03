package dev.facturador.user.application;

import dev.facturador.shared.infrastructure.JWT;
import org.springframework.stereotype.Component;

public final class JWTOfUser implements JWT<String> {

    @Override
    public String createUserByToken(String authHeader) {
        if (this.verifyToken(authHeader)) {
            var token = authHeader.substring("Bearer ".length());
            var username = this.createDecoder(token).getSubject();
            return username;
        }
        return "null";
    }

}
