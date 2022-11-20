package dev.facturador.global.domain.abstractcomponents.event;

@FunctionalInterface
public interface PortEventBus {
    <T> T handle(Event<T> event) throws Exception;
}
