package dev.facturador.shared.application.querys;

public interface QueryBus {

    <T> T handle(Query<T> query) throws Exception;
}
