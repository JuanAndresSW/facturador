package dev.facturador.global.application.commands;

public interface CommandHandler<T extends Command> {

    void handle(T command) throws Exception;
}
