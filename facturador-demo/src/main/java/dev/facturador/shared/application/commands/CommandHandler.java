package dev.facturador.shared.application.commands;

public interface CommandHandler<T extends Command> {

    void handle(T command) throws Exception;
}
