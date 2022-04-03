package dev.facturador.user.application.usecase;

import dev.facturador.user.domain.UserIdUsername;
import dev.facturador.user.domain.UserRepository;
import dev.facturador.user.domain.subdomain.UserAvatar;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserGetAvatarUseCase {
    @Autowired
    private UserRepository repository;

    public UserAvatar handle(UserIdUsername userIndex) {
        var user = repository.findByUsername(userIndex.getUsername());
        var userAvatar = user.get().getAvatarUser();
        if(userAvatar == null){
            return null;
        }
        return userAvatar;
    }
}
