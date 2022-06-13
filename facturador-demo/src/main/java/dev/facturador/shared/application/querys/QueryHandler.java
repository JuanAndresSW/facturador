package dev.facturador.shared.application.querys;

public interface QueryHandler<T, U extends Query<T>> {

    T handle(U query) throws Exception;
}
