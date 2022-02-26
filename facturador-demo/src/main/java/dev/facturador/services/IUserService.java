package dev.facturador.services;

import dev.facturador.bo.LoginBo;
import dev.facturador.entities.Usuarios;

import java.util.Optional;

public interface IUserService {
    Usuarios getUserByUsername(Usuarios username);

    Optional<Usuarios> getUserWithCrdentials(String usernameOrEmail);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
