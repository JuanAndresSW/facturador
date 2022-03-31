package dev.facturador.mainaccount.infrastructure.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.facturador.mainaccount.domain.MainAccountIdUsername;
import dev.facturador.mainaccount.domain.MainAccountRegister;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class MainAccountRegisterSerializer extends JsonSerializer<MainAccountRegister> {

    @Override
    public Class<MainAccountRegister> handledType(){
        return MainAccountRegister.class;
    }

    @Override
    public void serialize(MainAccountRegister value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("userRegister");
        serializers.defaultSerializeValue(value.userRegister(), gen);
        gen.writeFieldName("traderRegister");
        serializers.defaultSerializeValue(value.traderRegister(), gen);
        gen.writeEndObject();
    }
}
