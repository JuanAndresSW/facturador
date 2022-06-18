package dev.facturador.user.application.query;

import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.user.application.usecase.UserGetAvatarUseCase;
import dev.facturador.user.domain.exception.UserDontHaveAvatar;
import org.springframework.stereotype.Component;

@Component
public class UserGetAvatarQueyHandler implements QueryHandler<String, UserGetAvatarQuery> {
    private final UserGetAvatarUseCase useCase;

    public UserGetAvatarQueyHandler(UserGetAvatarUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public String handle(UserGetAvatarQuery query) throws Exception {
        var avatar = useCase.handle(query.getUserIdUsername());
        if (avatar.getAvatar() == null) {
            throw new UserDontHaveAvatar("Usuario no tiene avatar");
        }
        return avatar.getAvatar();
    }
}
