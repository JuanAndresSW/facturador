package dev.facturador.global.domain.abstractcomponents.querys;

/**
 * QueryHandler Generico
 */
@FunctionalInterface
public interface QueryHandler<T, U extends Query<T>> {

    T handle(U query) throws Exception;
}
