package dev.facturador.global.domain.abstractcomponents.event;

@FunctionalInterface
public interface PortEventHandler<T, U extends Event<T>> {
    T handle(U event) throws Exception;
}
