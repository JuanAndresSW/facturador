package dev.facturador.entitiesjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class Account {
    private User user;
    private Trader trader;
}
