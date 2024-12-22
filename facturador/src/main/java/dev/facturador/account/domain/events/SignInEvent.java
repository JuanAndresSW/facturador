package dev.facturador.account.domain.events;

import dev.facturador.global.domain.abstractcomponents.event.Event;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.LinkedHashMap;

@Data
@Builder
public class SignInEvent extends Event<LinkedHashMap<String, String>> {
    private final BodyInserters.FormInserter<String> body;
}
