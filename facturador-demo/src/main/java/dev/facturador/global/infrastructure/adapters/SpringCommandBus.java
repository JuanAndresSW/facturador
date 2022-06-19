package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.application.commands.Command;
import dev.facturador.global.application.commands.CommandBus;
import dev.facturador.global.application.commands.CommandHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Clase que recibe un comando y se encarga de buscar a que Handler le pertenece*/
@Component
@Primary
public class SpringCommandBus implements CommandBus {
    private final Map<Class, CommandHandler> handlers;

    /**Se encarga de buscar todos los comandos*/
    public SpringCommandBus(List<CommandHandler> commandHandlerImplementations) {
        this.handlers = new HashMap<>();
        commandHandlerImplementations.forEach(commandHandler -> {
            Class<?> commandClass = getCommandClass(commandHandler);
            handlers.put(commandClass, commandHandler);
        });
    }
    /**Busca un handler para el comando y ejecuta este si lo encuentra*/
    @Override
    public void handle(Command command) throws Exception {
        //Si no existe un Handler con este comando da error
        if (!handlers.containsKey(command.getClass())) {
            throw new Exception(String.format("No handler for %s", command.getClass().getName()));
        }
        //Si no dio error entonces solo busca la implementacion y ejecuta su metodo handle
        handlers.get(command.getClass()).handle(command);
    }

    /**Busca la clase de la implementacion utilizando la libreria de {@link java.lang.reflect}*/
    public Class<?> getCommandClass(CommandHandler handler) {
        Type commandInterface = ((ParameterizedType) handler.getClass()
                .getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return getClass(commandInterface.getTypeName());
    }
    /**Una vez recupera el nombre del tipo del handler recupera la clase de esta*/
    public Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception ex) {
            return null;
        }
    }
}
