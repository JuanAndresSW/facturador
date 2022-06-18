package dev.facturador.global.application.commands;

public interface CommandBus {

    void handle(Command command) throws Exception;
}
