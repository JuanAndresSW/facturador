package dev.facturador.entitiesjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class User {
    private String username;
    private String email;
    private String password;
    private String avatar;

}
