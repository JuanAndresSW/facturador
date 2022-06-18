package dev.facturador.global.application.querys;

public interface QueryBus {

    <T> T handle(Query<T> query) throws Exception;
}
