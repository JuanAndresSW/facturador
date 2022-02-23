package dev.facturador.util;

import dev.facturador.dto.RegisterDto;
import dev.facturador.services.ITraderService;
import dev.facturador.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class SignUpUtil {

    /**
     * Invoca a los metodos con la logica para enviar o no los mensajes
     */
    public static String whenIndicesAreRepeatedReturnErrror(RegisterDto account, IUserService userService, ITraderService traderService) {
        Optional<String> comp = errorWhenExistsEmail(account, userService);
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = errorWhenExistsUsername(account, userService);
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = errorWhenExistsUniquekey(account, traderService);
        return comp.orElse(null);
    }

    /**
     * Comprueba que el Username no exista
     */
    public static Optional<String> errorWhenExistsUsername(RegisterDto account, IUserService service) {
        if (service.isExistsUserByUsername(account.getUserDto().username())) {
            return Optional.of("Nombre de usuario ya se encuentra en uso");
        }
        return Optional.empty();
    }

    /**
     * Comprueba si el Email no existe
     */
    public static Optional<String> errorWhenExistsEmail(RegisterDto account, IUserService service) {
        if (service.isExistsUserByEmail(account.getUserDto().email())) {
            return Optional.of("Email ya se encuentra en uso");
        }
        return Optional.empty();
    }

    /**
     * Comprueba que el cuit no exista
     */
    public static Optional<String> errorWhenExistsUniquekey(RegisterDto account, ITraderService service) {
        if (service.isExistsTraderByUniquekey(account.getTraderDto().code())) {
            return Optional.of("Cuit/Cuil ya se encuentra en uso");
        }
        return Optional.empty();
    }
}
