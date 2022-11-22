package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.domain.NotContainHandler;
import dev.facturador.global.domain.abstractcomponents.command.Command;
import dev.facturador.global.domain.abstractcomponents.query.Query;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * clase que decide que implementacion de los query handler debe ejecutar
 */
@Service
@Primary
public class QueryBus implements PortQueryBus {
    private final Map<Class, PortQueryHandler> handlers;

    /**
     * Se encarga de buscar todos los querys
     */
    public QueryBus(List<PortQueryHandler> portQueryHandlerImplementations) {
        this.handlers = new HashMap<>();
        portQueryHandlerImplementations.forEach(queryHandler -> {
            Class queryClass = getQueryClass(queryHandler);
            handlers.put(queryClass, queryHandler);
        });
    }

    /**
     * Busca un handler para la query y ejecuta este si lo encuentra
     */
    @Override
    public <T> T handle(Query<T> query) throws Exception {
        //Si no existe un Handler con este query da error
        ensuringContainsHandler(query);
        //Si no dio error entonces solo busca la implementacion y ejecuta su metodo handle
        return (T) handlers.get(query.getClass()).handle(query);
    }

    private void ensuringContainsHandler(Query query){
        if (!handlers.containsKey(query.getClass()))
            throw new NotContainHandler(String.format("No handler for %s", query.getClass().getName()));
    }

    /**
     * Busca la clase de la query que este relacionada con la instancia del handler pasada
     *
     * @param handler Instancia de algun query handler
     * @return query object class
     */
    public Class<?> getQueryClass(PortQueryHandler handler) {
        var methods = Arrays.stream(handler.getClass().getMethods())
                .toList().stream()
                .filter(x -> x.getName().equalsIgnoreCase("handle"))
                .filter(x -> !x.getParameterTypes()[0].getSimpleName().startsWith("Query"))
                .toList();
        return getClass(methods
                .get(0).getParameterTypes()[0].getCanonicalName());
    }

    /**
     * Recupera un objeto Class en base el nombre de una clase
     */
    public Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception ex) {
            return null;
        }
    }
}
