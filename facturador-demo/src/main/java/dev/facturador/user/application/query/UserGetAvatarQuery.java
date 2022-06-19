package dev.facturador.user.application.query;

import dev.facturador.global.application.querys.Query;
import dev.facturador.user.domain.UserIdUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**Query para recuperar el userAvatar*/
@Getter
@AllArgsConstructor
public class UserGetAvatarQuery extends Query<String> {

    private UserIdUsername userIdUsername;
    /**Builder de la Query*/
    public static class Builder {
        private UserIdUsername userIdUsername;

        public static UserGetAvatarQuery.Builder getInstance() {
            return new UserGetAvatarQuery.Builder();
        }

        public UserGetAvatarQuery.Builder userIdUsername(UserIdUsername userIdUsername) {
            this.userIdUsername = userIdUsername;
            return this;
        }

        public UserGetAvatarQuery build() {
            return new UserGetAvatarQuery(userIdUsername);
        }
    }
}
