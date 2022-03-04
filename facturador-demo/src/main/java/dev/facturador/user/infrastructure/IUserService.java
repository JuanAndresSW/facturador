package dev.facturador.user.infrastructure;

import dev.facturador.user.domain.Usuarios;

import java.util.Optional;

public interface IUserService {
    Usuarios getUserByUsername(Usuarios username);

    Optional<Usuarios> getUserWithCrdentials(String usernameOrEmail);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
