package dev.facturador.user.application.query;

import dev.facturador.shared.application.querybus.Query;
import dev.facturador.user.domain.UserIdUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserGetAvatarQuery extends Query<String> {

    private UserIdUsername userIdUsername;

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
