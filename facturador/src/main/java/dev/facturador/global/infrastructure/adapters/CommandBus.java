package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.domain.NotContainHandler;
import dev.facturador.global.domain.abstractcomponents.command.Command;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que recibe un comando y se encarga de buscar a que Handler le pertenece
 */
@Service
@Primary
public class CommandBus implements PortCommandBus {
    private final Map<Class<? extends Command>, PortCommandHandler> handlers;

    /**
     * Se encarga de buscar todos los comandos
     */
    public CommandBus(List<PortCommandHandler> commandHandlerImplementations) {

        this.handlers = new HashMap<>();
        commandHandlerImplementations.forEach(commandHandler -> {
            Class<? extends Command> commandClass =
                    getCommandClass(commandHandler);
            handlers.put(commandClass, commandHandler);
        });
    }

    /**
     * Busca un handler para el comando y ejecuta este si lo encuentra
     */
    @Override
    public void handle(Command command) throws Exception {
        //Si no existe un Handler con este comando da error
        ensuringContainsHandler(command);
        //Si no dio error entonces solo busca la implementacion y ejecuta su metodo handle
        handlers.get(command.getClass()).handle(command);
    }

    private void ensuringContainsHandler(Command command){
        if (!handlers.containsKey(command.getClass()))
            throw new NotContainHandler(String.format("No handler for %s", command.getClass().getName()));
    }

    /**
     * Busca la clase del comando que este relacionada con la instancia del handler pasada
     *
     * @param handler Instancia de algun command handler
     * @return command object class
     */
    public Class<? extends Command> getCommandClass(PortCommandHandler handler) {
        var methods = Arrays.stream(handler.getClass().getMethods()).toList()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase("handle"))
                .filter(x -> !x.getParameterTypes()[0].getSimpleName().startsWith("Command"))
                .toList();
        return (Class<? extends Command>) getClass(methods
                .get(0).getParameterTypes()[0].getCanonicalName());
    }

    /**
     * Recupera un objeto Class en base el nombre de una clase
     */
    public Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception ex) {
            return null;
        }
    }
}
