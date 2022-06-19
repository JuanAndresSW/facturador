package dev.facturador.shared.application.commands;

public interface CommandBus {

    void handle(Command command) throws Exception;
}
