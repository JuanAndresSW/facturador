package dev.facturador.user.infrastructure;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.user.application.query.GetUserAvatarQuery;
import dev.facturador.user.domain.UserIdUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.ok;

/**
 * EndPoint para recuperar el avatar de usuario
 */
@RestController
@RequestMapping(path = "/api/users")
public class GetAvatarUserResource {

    private final PortQueryBus queryBus;

    @Autowired
    public GetAvatarUserResource(PortQueryBus queryBus) {
        this.queryBus = queryBus;
    }

    /**
     * Recupera el avatar del usuario
     *
     * @param username Nombre de usuario al que le pertenece el avatar
     * @return avatar de usuario
     */
    @GetMapping("/{username}")
    public Mono<HttpEntity<String>> getUserAvatar(@PathVariable String username) throws Exception {
        GetUserAvatarQuery query = GetUserAvatarQuery.Builder.getInstance()
                .userIdUsername(UserIdUsername.starter(username)).build();
        var avatar = queryBus.handle(query);

        return Mono.just(avatar).map(r -> ok().body(r));
    }
}
