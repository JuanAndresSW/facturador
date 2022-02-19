package dev.facturador.services;

import dev.facturador.dto.LoginDto;
import dev.facturador.entities.Usuarios;

import java.util.Optional;

public interface IUserService {
    Usuarios getUserByUsername(Usuarios username);
    Optional<Usuarios> getUserWithCrdentials(LoginDto user);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
