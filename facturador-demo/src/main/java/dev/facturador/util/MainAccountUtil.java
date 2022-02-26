package dev.facturador.util;

import dev.facturador.bo.RegisterBo;
import dev.facturador.services.ITraderService;
import dev.facturador.services.IUserService;
import dev.facturador.services.impl.TraderService;
import dev.facturador.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MainAccountUtil {
    private IUserService userService;
    private ITraderService traderService;
    @Autowired
    public MainAccountUtil(IUserService userService, ITraderService traderService){
        this.userService = userService;
        this.traderService = traderService;
    }

    /**
     * Invoca a los metodos con la logica para enviar o no los mensajes
     */
    public String whenIndicesAreRepeatedReturnErrror(RegisterBo account) {
        Optional<String> comp = errorWhenExistsEmail(account);
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = errorWhenExistsUsername(account);
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = errorWhenExistsUniquekey(account);
        return comp.orElse(null);
    }

    /**
     * Comprueba que el Username no exista
     */
    public Optional<String> errorWhenExistsUsername(RegisterBo account) {
        if (userService.existsByUsername(account.getUserBo().username())) {
            return Optional.of("Nombre de usuario ya se encuentra en uso");
        }
        return Optional.empty();
    }

    /**
     * Comprueba si el Email no existe
     */
    public Optional<String> errorWhenExistsEmail(RegisterBo account) {
        if (userService.existsByEmail(account.getUserBo().email())) {
            return Optional.of("Email ya se encuentra en uso");
        }
        return Optional.empty();
    }

    /**
     * Comprueba que el cuit no exista
     */
    public Optional<String> errorWhenExistsUniquekey(RegisterBo account) {
        if (traderService.isExistsTraderByUniquekey(account.getTraderBo().code())) {
            return Optional.of("Cuit/Cuil ya se encuentra en uso");
        }
        return Optional.empty();
    }


}
