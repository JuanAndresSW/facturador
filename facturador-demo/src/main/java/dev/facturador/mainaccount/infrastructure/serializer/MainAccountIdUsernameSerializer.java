package dev.facturador.mainaccount.infrastructure.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.facturador.mainaccount.domain.MainAccountIdUsername;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class MainAccountIdUsernameSerializer extends JsonSerializer<MainAccountIdUsername> {

    @Override
    public Class<MainAccountIdUsername> handledType(){
        return MainAccountIdUsername.class;
    }

    @Override
    public void serialize(MainAccountIdUsername value, JsonGenerator generator, SerializerProvider serializerProvider)
            throws IOException {
        generator.writeString(value.getUsername());
    }
}
