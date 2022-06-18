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

@Component
@Primary
public class SpringCommandBus implements CommandBus {
    private final Map<Class, CommandHandler> handlers;

    public SpringCommandBus(List<CommandHandler> commandHandlerImplementations) {
        this.handlers = new HashMap<>();
        commandHandlerImplementations.forEach(commandHandler -> {
            Class<?> commandClass = getCommandClass(commandHandler);
            handlers.put(commandClass, commandHandler);
        });
    }

    @Override
    public void handle(Command command) throws Exception {
        if (!handlers.containsKey(command.getClass())) {
            throw new Exception(String.format("No handler for %s", command.getClass().getName()));
        }
        handlers.get(command.getClass()).handle(command);
    }

    public Class<?> getCommandClass(CommandHandler handler) {
        Type commandInterface = ((ParameterizedType) handler.getClass()
                .getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return getClass(commandInterface.getTypeName());
    }

    public Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception ex) {
            return null;
        }
    }
}
