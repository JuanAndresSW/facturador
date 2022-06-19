package dev.facturador.global.application.commands;

/**CommandHandler Generico*/
@FunctionalInterface
public interface CommandHandler<T extends Command> {

    void handle(T command) throws Exception;
}
