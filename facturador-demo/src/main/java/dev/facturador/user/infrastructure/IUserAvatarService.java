package dev.facturador.user.infrastructure;

import dev.facturador.user.domain.AvatarUsuario;

public interface IUserAvatarService {

    AvatarUsuario getAvatarUsuarioByUsername(String username);
}
