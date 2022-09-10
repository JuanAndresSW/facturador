package dev.facturador.user.infrastructure;

import dev.facturador.global.domain.abstractcomponents.querys.QueryBus;
import dev.facturador.user.application.query.UserGetAvatarQuery;
import dev.facturador.user.domain.UserIdUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EndPoint para recuperar el avatar de usuario
 */
@RestController
@RequestMapping(path = "/api/users")
public class GetAvatarUserResource {

    private final QueryBus queryBus;

    @Autowired
    public GetAvatarUserResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    /**
     * Recupera el avatar del usuario
     *
     * @param username Nombre de usuario al que le pertenece el avatar
     * @return avatar de usuario
     */
    @GetMapping("/{username}")
    public ResponseEntity<String> getUserAvatar(@PathVariable String username) throws Exception {
        UserGetAvatarQuery query = UserGetAvatarQuery.Builder.getInstance()
                .userIdUsername(UserIdUsername.starter(username)).build();
        var avatar = queryBus.handle(query);
        return ResponseEntity.ok().body(avatar);
    }
}
