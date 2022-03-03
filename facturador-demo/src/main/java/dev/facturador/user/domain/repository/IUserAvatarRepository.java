package dev.facturador.user.domain.repository;

import dev.facturador.user.domain.AvatarUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserAvatarRepository extends JpaRepository<AvatarUsuario, Long> {

    Optional<AvatarUsuario> findByUsuarioUsername(String username);
}
