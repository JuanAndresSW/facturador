package dev.facturador.services;

import dev.facturador.dto.LoginDto;
import dev.facturador.entities.Usuarios;

public interface IUserService {
    Usuarios getUserByUsername(Usuarios username);
    Usuarios getUserWithCrdentials(LoginDto user);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
