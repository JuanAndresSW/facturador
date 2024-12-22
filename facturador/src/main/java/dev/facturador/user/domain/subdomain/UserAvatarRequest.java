package dev.facturador.user.domain.subdomain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class UserAvatarRequest {
    @Lob
    @NotEmpty
    private final String avatar;
    @NotEmpty
    private final String nameAvatar;
    @NotEmpty
    private final String mymeType;
    @NotEmpty
    private final String username;
}
