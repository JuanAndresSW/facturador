package dev.facturador.user.infrastructure;

import dev.facturador.shared.application.querybus.QueryBus;
import dev.facturador.user.application.query.UserGetAvatarQuery;
import dev.facturador.user.domain.UserIdUsername;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping(path = "/api/users")
public class GetAvatarUserResource {
    @Autowired
    private QueryBus queryBus;

    @GetMapping("/{username}")
    public ResponseEntity<String> getUserAvatar(@PathVariable @NotEmpty String username) throws Exception {
        UserGetAvatarQuery query = UserGetAvatarQuery.Builder.getInstance()
                .userIdUsername(UserIdUsername.starter(username)).build();
        var avatar = queryBus.handle(query);
        return ResponseEntity.ok().body(avatar);
    }
}
