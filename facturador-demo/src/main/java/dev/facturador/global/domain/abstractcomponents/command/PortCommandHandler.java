package dev.facturador.global.domain.abstractcomponents.command;

/**
 * CommandHandler Generico
 */
@FunctionalInterface
public interface PortCommandHandler<T extends Command> {
    void handle(T command) throws Exception;
}
