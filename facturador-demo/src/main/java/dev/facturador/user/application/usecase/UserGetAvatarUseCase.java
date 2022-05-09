package dev.facturador.user.application.usecase;

import dev.facturador.user.domain.UserIdUsername;
import dev.facturador.user.domain.UserRepository;
import dev.facturador.user.domain.subdomain.UserAvatar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserGetAvatarUseCase {
    @Autowired
    private UserRepository repository;

    public UserAvatar handle(UserIdUsername userIndex) {
        var user = repository.findByUsername(userIndex.getUsername());
        if (user.get().getAvatarUser() == null) {
            return null;
        }
        var userAvatar = user.get().getAvatarUser();
        log.info("Avatar is: {}", userAvatar);
        return userAvatar;
    }
}
