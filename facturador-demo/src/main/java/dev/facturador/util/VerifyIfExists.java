package dev.facturador.util;

import dev.facturador.dto.RegisterDto;
import dev.facturador.services.ITraderService;
import dev.facturador.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public final class VerifyIfExists {

    /**
     * Comprueba que el Username no exista
     */
    public static Optional<String> errorIfExistUsername(RegisterDto account, IUserService userService){
        if(userService.existsByUsername(account.getUserDto().username())){
            return Optional.of("Nombre de usuario ya se encuentra en uso");
        }
        return Optional.empty();
    }

    /**
     * Comprueba si el Email no existe
     */
    public static  Optional<String> errorIfExistEmail(RegisterDto account, IUserService userService){
        if(userService.existsByEmail(account.getUserDto().email())){
            return Optional.of("Email ya se encuentra en uso");
        }
        return Optional.empty();
    }

    /**
     * Comprueba que el cuit no exista
     */
    public static Optional<String> errorIfExistUniqueKey(RegisterDto account, ITraderService traderService){
        if(traderService.existsByUniqueKey(account.getTraderDto().code())){
            return Optional.of("Cuit/Cuil ya se encuentra en uso");
        }
        return Optional.empty();
    }



}
