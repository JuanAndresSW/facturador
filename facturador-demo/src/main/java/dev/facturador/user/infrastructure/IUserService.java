package dev.facturador.user.infrastructure;

import dev.facturador.user.domain.User;

import java.util.Optional;

public interface IUserService {
    User getUserByUsername(User username);

    Optional<User> getUserWithCrdentials(String usernameOrEmail);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
