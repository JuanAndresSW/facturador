package dev.facturador.account.infrastructure.handler;

import dev.facturador.account.domain.events.SignInEvent;
import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import dev.facturador.global.domain.abstractcomponents.event.PortEventHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

//Evento para llamar al Login de Spring
@Service
@AllArgsConstructor
public class SignInEventHandler
        implements PortEventHandler<LinkedHashMap<String, String>, SignInEvent> {
    @Autowired
    private final ReactiveRequest<Class, String> reactiveRequest;

    @Override
    public LinkedHashMap<String, String> handle(SignInEvent event) throws Exception {
        var headers =reactiveRequest.makeRequestNotHeader(
                "POST",
                "/login",
                event.getBody(),
                MediaType.APPLICATION_FORM_URLENCODED,
                String.class).getHeaders();

        return new LinkedHashMap(
                Map.of("IDTrader", headers.get("IDTrader").get(0),
                        "username", headers.get("username").get(0),
                        "accessToken", headers.get("accessToken").get(0),
                        "refreshToken", headers.get("refreshToken").get(0)));
    }
}
