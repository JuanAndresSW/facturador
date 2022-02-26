package dev.facturador.util;

import dev.facturador.bo.RegisterBo;
import dev.facturador.services.IMainAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MainAccountUtil {

    /**
     * Invoca a los metodos con la logica para enviar o no los mensajes
     */
    public String whenIndicesAreRepeatedReturnErrror(RegisterBo account, IMainAccountService mainAccountService) {
        Optional<String> comp = errorWhenExistsEmail(account, mainAccountService);
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = errorWhenExistsUsername(account, mainAccountService);
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = errorWhenExistsUniquekey(account, mainAccountService);
        return comp.orElse(null);
    }

    /**
     * Comprueba que el Username no exista
     */
    public Optional<String> errorWhenExistsUsername(RegisterBo account, IMainAccountService mainAccountService) {
        if (mainAccountService.existsByUsernameOfUsuarios(account.getUserBo().username())) {
            return Optional.of("Nombre de usuario ya se encuentra en uso");
        }
        return Optional.empty();
    }

    /**
     * Comprueba si el Email no existe
     */
    public Optional<String> errorWhenExistsEmail(RegisterBo account, IMainAccountService mainAccountService) {
        if (mainAccountService.existsByEmailOfUsuarios(account.getUserBo().email())) {
            return Optional.of("Email ya se encuentra en uso");
        }
        return Optional.empty();
    }

    /**
     * Comprueba que el cuit no exista
     */
    public Optional<String> errorWhenExistsUniquekey(RegisterBo account, IMainAccountService mainAccountService) {
        if (mainAccountService.existsByUniqueKeyOfTrader(account.getTraderBo().code())) {
            return Optional.of("Cuit/Cuil ya se encuentra en uso");
        }
        return Optional.empty();
    }


}
