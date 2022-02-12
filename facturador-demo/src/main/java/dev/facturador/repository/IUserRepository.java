package dev.facturador.repository;

import dev.facturador.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interfaz para llaar al repositorio de Detalles de Cuenta
 */
public interface IUserRepository extends JpaRepository<Usuarios, Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Usuarios> findByUsername(String username);
    Optional<Usuarios> findByUsernameOrEmail(String username,String email);
    Usuarios findByEmail(String email);
}
