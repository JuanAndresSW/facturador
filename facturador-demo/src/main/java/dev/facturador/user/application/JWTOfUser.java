package dev.facturador.user.application;

import dev.facturador.shared.infrastructure.JWT;

public final class JWTOfUser implements JWT<String> {

    @Override
    public String createUserByToken(String authHeader) {
        if (this.verifyToken(authHeader)) {
            var token = authHeader.substring("Bearer ".length());
            var email = this.createDecoder(token).getSubject();
            return email;
        }
        return "null";
    }

}
