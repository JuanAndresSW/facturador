package dev.facturador.shared.application.comandbus;

public interface CommandBus {

    void handle(Command command) throws Exception;
}
