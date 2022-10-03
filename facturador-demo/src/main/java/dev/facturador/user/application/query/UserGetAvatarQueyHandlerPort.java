package dev.facturador.user.application.query;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.security.domain.exception.ResourceNotFound;
import dev.facturador.user.application.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manejador de la Quey {@link UserGetAvatarQuery}
 */
@AllArgsConstructor
@Service
public class UserGetAvatarQueyHandlerPort implements PortQueryHandler<String, UserGetAvatarQuery> {
    @Autowired
    private final UserRepository repository;

    @Override
    public String handle(UserGetAvatarQuery query) throws Exception {
        var user = repository.findByUsername(query.getUserIdUsername().getUsername());

        if (user.isEmpty()) {
            throw new ResourceNotFound("Avatar not found");
        }

        return user.get().getUserAvatar().getAvatar();
    }
}
