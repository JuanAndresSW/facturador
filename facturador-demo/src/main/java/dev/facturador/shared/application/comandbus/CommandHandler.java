package dev.facturador.shared.application.comandbus;

public interface CommandHandler<T extends Command> {

    void handle(T command) throws Exception;
}
