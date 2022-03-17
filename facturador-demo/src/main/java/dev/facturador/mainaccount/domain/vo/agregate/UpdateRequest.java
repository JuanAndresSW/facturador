package dev.facturador.mainaccount.domain.vo.agregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.mainaccount.domain.vo.TraderUpdate;
import dev.facturador.mainaccount.domain.vo.UserUpdate;
import lombok.Data;

import javax.validation.Valid;

@Data
public final class UpdateRequest {
    @JsonProperty("user")
    @Valid
    private UserUpdate user;

    @JsonProperty("trader")
    @Valid
    private TraderUpdate trader;
}
