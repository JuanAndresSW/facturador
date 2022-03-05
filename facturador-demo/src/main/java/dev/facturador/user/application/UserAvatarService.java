package dev.facturador.user.application;

import dev.facturador.user.domain.UserAvatar;
import dev.facturador.user.domain.repository.IUserAvatarRepository;
import dev.facturador.user.infrastructure.IUserAvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserAvatarService implements IUserAvatarService {

    @Autowired
    private IUserAvatarRepository repository;

    @Override
    public UserAvatar getAvatarUsuarioByUsername(String username) {
        var userAvatar = repository.findByUserUsername(username);
        if (userAvatar.isEmpty()) {
            return null;
        }
        return userAvatar.get();
    }
}
