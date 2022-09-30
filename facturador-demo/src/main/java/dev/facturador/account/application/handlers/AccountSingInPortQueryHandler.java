package dev.facturador.account.application.handlers;

import dev.facturador.account.domain.events.ARequestLoginEvent;
import dev.facturador.account.domain.querys.AccountSingInQuery;
import dev.facturador.global.domain.abstractcomponents.event.PortEventBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.LinkedHashMap;

/**
 * Manejador de la Query {@link AccountSingInQuery}
 */
@AllArgsConstructor
@Service
public class AccountSingInPortQueryHandler implements PortQueryHandler<LinkedHashMap<String, String>, AccountSingInQuery> {
    @Autowired
    private final PortEventBus portEventBus;

    /**
     * Solo sede la operacion al caso de uso
     */
    @Override
    public LinkedHashMap<String, String> handle(AccountSingInQuery query) throws Exception {

        var event = ARequestLoginEvent.builder()
                .body(BodyInserters.fromFormData(query.getKeys()))
                .build();
        var response = portEventBus.handle(event);
        //En que caso de que falle
        if (response == null) {
            throw new Exception("Response in login is null");
        }

        return response;
    }
}
