package dev.facturador.useravatar.infrastructure;

import dev.facturador.useravatar.domain.AvatarUsuario;

public interface IUserAvatarService {

    AvatarUsuario getAvatarUsuarioByUsername(String username);
}
