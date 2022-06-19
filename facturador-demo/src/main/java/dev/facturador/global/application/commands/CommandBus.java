package dev.facturador.global.application.commands;

/**CommandBus generico*/
@FunctionalInterface
public interface CommandBus {

    void handle(Command command) throws Exception;
}
