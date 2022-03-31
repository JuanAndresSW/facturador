package dev.facturador.shared.infrastructure;

import dev.facturador.mainaccount.domain.MainAccountIdUsername;
import dev.facturador.mainaccount.domain.MainAccountRegister;
import dev.facturador.mainaccount.infrastructure.serializer.MainAccountIdUsernameSerializer;
import dev.facturador.mainaccount.infrastructure.serializer.MainAccountRegisterSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class SpringRestConfiguration {

    @Bean
    @Order(value = 10)
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return builder ->{
            builder.serializerByType(MainAccountIdUsername.class, new MainAccountIdUsernameSerializer());
            builder.serializerByType(MainAccountRegister.class, new MainAccountRegisterSerializer());
        };
    }
}
