package dev.facturador.user.application.query;

import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import dev.facturador.user.application.usecase.UserGetAvatarUseCase;
import org.springframework.stereotype.Component;

/**Manejador de la Quey {@link UserGetAvatarQuery}*/
@Component
public class UserGetAvatarQueyHandler implements QueryHandler<String, UserGetAvatarQuery> {
    private final UserGetAvatarUseCase useCase;

    public UserGetAvatarQueyHandler(UserGetAvatarUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public String handleGetBranch(UserGetAvatarQuery query) throws Exception {
        //Recupera el avatar
        var avatar = useCase.handle(query.getUserIdUsername());
        //Verifica que no sea nulo
        if (avatar.getAvatar() == null) {
            throw new ResourceNotFound("Avatar not found");
        }
        return avatar.getAvatar();
    }
}
