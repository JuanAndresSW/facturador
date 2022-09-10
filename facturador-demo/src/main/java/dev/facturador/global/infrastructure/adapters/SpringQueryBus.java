package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.domain.abstractcomponents.querys.Query;
import dev.facturador.global.domain.abstractcomponents.querys.QueryBus;
import dev.facturador.global.domain.abstractcomponents.querys.QueryHandler;
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
public class SpringQueryBus implements QueryBus {
    private final Map<Class, QueryHandler> handlers;

    /**
     * Se encarga de buscar todos los querys
     */
    public SpringQueryBus(List<QueryHandler> queryHandlerImplementations) {
        this.handlers = new HashMap<>();
        queryHandlerImplementations.forEach(queryHandler -> {
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
        if (!handlers.containsKey(query.getClass())) {
            throw new Exception(String.format("No handler for %s", query.getClass().getName()));
        }
        //Si no dio error entonces solo busca la implementacion y ejecuta su metodo handle
        return (T) handlers.get(query.getClass()).handle(query);
    }

    /**
     * Busca la clase de la query que este relacionada con la instancia del handler pasada
     *
     * @param handler Instancia de algun query handler
     * @return query object class
     */
    public Class<?> getQueryClass(QueryHandler handler) {
        var methods = Arrays.stream(handler.getClass().getMethods())
                .toList().stream()
                .filter(x -> x.getName().equalsIgnoreCase("handle"))
                .filter(x -> !x.getParameterTypes()[0].getSimpleName().startsWith("Query"))
                .collect(Collectors.toList());

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
