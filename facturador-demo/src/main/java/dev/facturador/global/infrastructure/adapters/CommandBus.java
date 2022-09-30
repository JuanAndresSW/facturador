package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.domain.abstractcomponents.command.Command;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase que recibe un comando y se encarga de buscar a que Handler le pertenece
 */
@Service
@Primary
public class CommandBus implements PortCommandBus {
    private final Map<Class, PortCommandHandler> handlers;

    /**
     * Se encarga de buscar todos los comandos
     */
    public CommandBus(List<PortCommandHandler> portCommandHandlerImplementations) {

        this.handlers = new HashMap<>();
        portCommandHandlerImplementations.forEach(commandHandler -> {
            Class<?> commandClass =
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
        if (!handlers.containsKey(command.getClass())) {
            throw new Exception(String.format("No handler for %s", command.getClass().getName()));
        }
        //Si no dio error entonces solo busca la implementacion y ejecuta su metodo handle
        handlers.get(command.getClass()).handle(command);
    }

    /**
     * Busca la clase del comando que este relacionada con la instancia del handler pasada
     *
     * @param handler Instancia de algun command handler
     * @return command object class
     */
    public Class<?> getCommandClass(PortCommandHandler handler) {
        var methods = Arrays.stream(handler.getClass().getMethods()).toList()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase("handle"))
                .filter(x -> !x.getParameterTypes()[0].getSimpleName().startsWith("Command"))
                .collect(Collectors.toList());
        return getClass(methods
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
