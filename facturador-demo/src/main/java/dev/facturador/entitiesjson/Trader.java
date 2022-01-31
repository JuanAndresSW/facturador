package dev.facturador.entitiesjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class Trader {
    private String businessName;
    private String vatCategory;
    private String code;
    private String grossIncome;
}
