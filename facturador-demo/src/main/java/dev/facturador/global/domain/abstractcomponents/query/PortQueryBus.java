package dev.facturador.global.domain.abstractcomponents.query;

/**
 * QueryBus generico
 */
@FunctionalInterface
public interface PortQueryBus {

    <T> T handle(Query<T> query) throws Exception;

}
