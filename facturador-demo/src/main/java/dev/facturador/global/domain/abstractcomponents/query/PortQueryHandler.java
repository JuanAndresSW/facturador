package dev.facturador.global.domain.abstractcomponents.query;

/**
 * QueryHandler Generico
 */
@FunctionalInterface
public interface PortQueryHandler<T, U extends Query<T>> {

    T handle(U query) throws Exception;
}
