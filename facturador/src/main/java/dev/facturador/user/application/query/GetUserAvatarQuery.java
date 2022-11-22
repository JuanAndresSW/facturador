package dev.facturador.user.application.query;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import dev.facturador.user.domain.UserIdUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Query para recuperar el userAvatar
 */
@Getter
@AllArgsConstructor
public class GetUserAvatarQuery extends Query<String> {
    private final UserIdUsername userIdUsername;

    /**
     * Builder de la Query
     */
    public static class Builder {
        private UserIdUsername userIdUsername;

        public static GetUserAvatarQuery.Builder getInstance() {
            return new GetUserAvatarQuery.Builder();
        }

        public GetUserAvatarQuery.Builder userIdUsername(UserIdUsername userIdUsername) {
            this.userIdUsername = userIdUsername;
            return this;
        }

        public GetUserAvatarQuery build() {
            return new GetUserAvatarQuery(userIdUsername);
        }
    }
}
