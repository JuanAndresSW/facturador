package dev.facturador.repository;

import dev.facturador.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Usuarios, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<Usuarios> findByUsername(String username);

    Optional<Usuarios> findByUsernameOrEmail(String username, String email);
}
