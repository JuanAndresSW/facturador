package dev.facturador.user.application.usecase;

import dev.facturador.user.domain.UserIdUsername;
import dev.facturador.user.domain.UserRepository;
import dev.facturador.user.domain.subdomain.UserAvatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**Caso de uso para la recuperación del avatar de usuario*/
@Service
@Transactional
public class UserGetAvatarUseCase {
    @Autowired
    private UserRepository repository;

    public UserAvatar handle(UserIdUsername userIndex) {
        var user = repository.findByUsername(userIndex.getUsername());
        if (user.isEmpty()) {
            return null;
        }

        return user.get().getUserAvatar();
    }
}
