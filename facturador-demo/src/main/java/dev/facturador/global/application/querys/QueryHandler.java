package dev.facturador.global.application.querys;

/**QueryHandler Generico*/
@FunctionalInterface
public interface QueryHandler<T, U extends Query<T>> {

    T handleGetBranch(U query) throws Exception;
}
