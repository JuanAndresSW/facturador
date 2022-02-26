package dev.facturador.services;

import dev.facturador.bo.LoginBo;
import dev.facturador.entities.Usuarios;

import java.util.Optional;

public interface IUserService {
    Usuarios getUserByUsername(Usuarios username);

    Optional<Usuarios> getUserWithCrdentials(LoginBo user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
