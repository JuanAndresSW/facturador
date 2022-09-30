package dev.facturador.global.domain.abstractcomponents.command;

/**
 * CommandBus generico
 */
@FunctionalInterface
public interface PortCommandBus {
    void handle(Command command) throws Exception;
}
