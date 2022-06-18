package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.application.querys.Query;
import dev.facturador.global.application.querys.QueryBus;
import dev.facturador.global.application.querys.QueryHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class SpringQueryBus implements QueryBus {
    private final Map<Class, QueryHandler> handlers;

    public SpringQueryBus(List<QueryHandler> queryHandlerImplementations) {
        this.handlers = new HashMap<>();
        queryHandlerImplementations.forEach(queryHandler -> {
            Class queryClass = getQueryClass(queryHandler);
            handlers.put(queryClass, queryHandler);
        });
    }

    @Override
    public <T> T handle(Query<T> query) throws Exception {
        if (!handlers.containsKey(query.getClass())) {
            throw new Exception(String.format("No handler for %s", query.getClass().getName()));
        }
        return (T) handlers.get(query.getClass()).handle(query);
    }

    public Class<?> getQueryClass(QueryHandler handler) {
        Type queryInterface = ((ParameterizedType) handler.getClass()
                .getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return getClass(queryInterface.getTypeName());
    }

    public Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception ex) {
            return null;
        }
    }
}
