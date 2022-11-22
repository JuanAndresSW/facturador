package dev.facturador.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserIdUsername {
    private String username;

    public static UserIdUsername starter(String username) {
        return new UserIdUsername(username);
    }
}
