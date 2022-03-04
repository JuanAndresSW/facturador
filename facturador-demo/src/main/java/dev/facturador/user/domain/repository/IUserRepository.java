package dev.facturador.user.domain.repository;

import dev.facturador.user.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<Usuarios, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<Usuarios> findByUsername(String username);

    Optional<Usuarios> findByUsernameOrEmail(String username, String email);
}
