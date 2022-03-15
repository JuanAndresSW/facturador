package dev.facturador.mainaccount.domain.vo.agregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.mainaccount.domain.vo.TraderUpdate;
import dev.facturador.mainaccount.domain.vo.UserUpdate;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public final class UpdateRequest {
    @JsonProperty("user")
    @NotNull
    @Valid
    private UserUpdate user;

    @JsonProperty("trader")
    @NotNull
    @Valid
    private TraderUpdate trader;
}
