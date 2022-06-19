package dev.facturador.global.application.querys;

/**QueryBus generico*/
@FunctionalInterface
public interface QueryBus {

    <T> T handle(Query<T> query) throws Exception;
}
