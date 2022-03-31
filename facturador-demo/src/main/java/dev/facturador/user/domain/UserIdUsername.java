package dev.facturador.user.domain;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public final class UserIdUsername {
    private String username;

    public static UserIdUsername starter(String username) {
        return new UserIdUsername(username);
    }
}
