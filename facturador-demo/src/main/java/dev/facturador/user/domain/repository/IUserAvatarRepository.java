package dev.facturador.user.domain.repository;

import dev.facturador.user.domain.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserAvatarRepository extends JpaRepository<UserAvatar, Long> {

    Optional<UserAvatar> findByUserUsername(String username);
}
