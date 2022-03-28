package dev.facturador.user.infrastructure;

import dev.facturador.user.domain.UserAvatar;

public interface IUserAvatarService {

    UserAvatar getAvatarUserByEmail(String email);
}
