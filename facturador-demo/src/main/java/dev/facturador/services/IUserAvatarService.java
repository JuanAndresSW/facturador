package dev.facturador.services;

import dev.facturador.entities.AvatarUsuario;

public interface IUserAvatarService {

    AvatarUsuario getAvatarUsuarioByUsername(String username);
}
