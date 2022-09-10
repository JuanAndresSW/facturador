package dev.facturador.global.domain.abstractcomponents.event;

@FunctionalInterface
public interface EventBus {
    <T> T handle(Event<T> event) throws Exception;
}
