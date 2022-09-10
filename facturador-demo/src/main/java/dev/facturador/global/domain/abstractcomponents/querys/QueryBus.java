package dev.facturador.global.domain.abstractcomponents.querys;

/**
 * QueryBus generico
 */
@FunctionalInterface
public interface QueryBus {

    <T> T handle(Query<T> query) throws Exception;

}
