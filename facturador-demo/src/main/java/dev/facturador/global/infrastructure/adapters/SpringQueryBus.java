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

/**Clase que recibe un query y se encarga de buscar a que Handler le pertenece*/
@Component
@Primary
public class SpringQueryBus implements QueryBus {
    private final Map<Class, QueryHandler> handlers;

    /**Se encarga de buscar todos los querys*/
    public SpringQueryBus(List<QueryHandler> queryHandlerImplementations) {
        this.handlers = new HashMap<>();
        queryHandlerImplementations.forEach(queryHandler -> {
            Class queryClass = getQueryClass(queryHandler);
            handlers.put(queryClass, queryHandler);
        });
    }

    /**Busca un handler para la query y ejecuta este si lo encuentra*/
    @Override
    public <T> T handle(Query<T> query) throws Exception {
        //Si no existe un Handler con este query da error
        if (!handlers.containsKey(query.getClass())) {
            throw new Exception(String.format("No handler for %s", query.getClass().getName()));
        }
        //Si no dio error entonces solo busca la implementacion y ejecuta su metodo handle
        return (T) handlers.get(query.getClass()).handle(query);
    }

    /**Busca la clase de la implementacion utilizando la libreria de {@link java.lang.reflect}*/
    public Class<?> getQueryClass(QueryHandler handler) {
        Type queryInterface = ((ParameterizedType) handler.getClass()
                .getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return getClass(queryInterface.getTypeName());
    }

    /**Una vez recupera el nombre del tipo del handler recupera la clase de esta*/
    public Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception ex) {
            return null;
        }
    }
}
