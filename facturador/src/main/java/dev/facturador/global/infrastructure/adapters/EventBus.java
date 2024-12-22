package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.domain.NotContainHandler;
import dev.facturador.global.domain.abstractcomponents.event.Event;
import dev.facturador.global.domain.abstractcomponents.event.PortEventBus;
import dev.facturador.global.domain.abstractcomponents.event.PortEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class EventBus implements PortEventBus {
    private final Map<Class, PortEventHandler> handlers;

    /**
     * Se encarga de buscar todos los querys
     */
    @Autowired
    public EventBus(List<PortEventHandler> portEventHandlers) {
        this.handlers = new HashMap<>();
        portEventHandlers.forEach(portEventHandler -> {
            Class eventClass = getEventClass(portEventHandler);
            handlers.put(eventClass, portEventHandler);
        });
    }

    /**
     * Busca un handler para el event, si lo encuentra ejecuta el evento
     */
    @Override
    public <T> T handle(Event<T> event) throws Exception {
        //Si no existe un Handler con este query da error
        eventContainHandler(event);
        //Si no dio error entonces solo busca la implementacion y ejecuta su metodo handle
        return (T) handlers.get(event.getClass()).handle(event);
    }

    private void eventContainHandler(Event event){
        if (!handlers.containsKey(event.getClass()))
            throw new NotContainHandler(String.format("No handler for %s", event.getClass().getName()));
    }

    /**
     * Busca la clase de la implementacion utilizando la libreria de {@link java.lang.reflect}
     */
    public Class<?> getEventClass(PortEventHandler handler) {
        var methods = Arrays.stream(handler.getClass().getMethods())
                .toList().stream()
                .filter(x -> x.getName().equalsIgnoreCase("handle"))
                .filter(x -> !x.getParameterTypes()[0].getSimpleName().startsWith("Event"))
                .toList();

        return getClass(methods
                .get(0).getParameterTypes()[0].getCanonicalName());
    }

    /**
     * Una vez recupera el nombre del tipo del handler recupera la clase de esta
     */
    public Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception ex) {
            return null;
        }
    }

}
