package dev.facturador.global.domain.abstractcomponents.commands;

/**
 * CommandHandler Generico
 */
@FunctionalInterface
public interface CommandHandler<T extends Command> {
    void handle(T command) throws Exception;
}
