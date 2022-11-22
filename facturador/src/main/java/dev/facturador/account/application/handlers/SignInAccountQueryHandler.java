package dev.facturador.account.application.handlers;

import dev.facturador.account.domain.events.SignInEvent;
import dev.facturador.global.domain.ResponseNull;
import dev.facturador.account.domain.querys.AccountSignInQuery;
import dev.facturador.global.domain.abstractcomponents.event.PortEventBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.LinkedHashMap;

/**
 * Manejador de la Query {@link AccountSignInQuery}
 */
@AllArgsConstructor
@Service
public class SignInAccountQueryHandler implements PortQueryHandler<LinkedHashMap<String, String>, AccountSignInQuery> {
    @Autowired
    private final PortEventBus eventBus;

    /**
     * Solo sede la operacion al caso de uso
     */
    @Override
    public LinkedHashMap<String, String> handle(AccountSignInQuery query) throws Exception {
        var event = SignInEvent.builder()
                .body(BodyInserters.fromFormData(query.getKeys()))
                .build();
        var response = eventBus.handle(event);

        //En que caso de que falle
        ensureNotNull(response);
        return response;
    }

    private void ensureNotNull(LinkedHashMap<String, String> response) {
        if (response == null) throw new ResponseNull("Response in login is null");
    }
}
