package dev.facturador.user.application.query;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.security.domain.exception.ResourceNotFound;
import dev.facturador.user.application.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manejador de la Quey {@link GetUserAvatarQuery}
 */
@AllArgsConstructor
@Service
public class GetUserAvatarQueryHandler implements PortQueryHandler<String, GetUserAvatarQuery> {
    @Autowired
    private final UserRepository repository;

    @Override
    public String handle(GetUserAvatarQuery query) throws Exception {
        var user = repository.findByUsername(query.getUserIdUsername().getUsername());

        if (user.isEmpty()) {
            throw new ResourceNotFound("Avatar not found");
        }

        return user.get().getUserAvatar().getAvatar();
    }
}
