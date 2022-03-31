package dev.facturador.user.application.usecase;

import dev.facturador.user.domain.UserIdUsername;
import dev.facturador.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UserGetAvatarUseCase {
    @Autowired
    private UserRepository repository;

    public String handle(UserIdUsername userIndex) {
        var avatar = repository.findAvatarUserAvatarByUsername(userIndex.getUsername());
        return avatar.orElse(null);
    }
}
