package dev.facturador.util;

import dev.facturador.dto.ErrorDetailsDto;
import dev.facturador.dto.RegisterDto;
import dev.facturador.services.ITraderService;
import dev.facturador.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.StringUtils;

import java.util.Date;

public final class Comprobaciones {

    @Autowired
    private static IUserService userService;
    @Autowired
    private static ITraderService serviceTrader;

    public ErrorDetailsDto errorIfExistUsername(RegisterDto account){
        if(userService.existsByUsername(account.getUserDto().getUsername())){
            return new ErrorDetailsDto(new Date(), "Nombre de usuario ya se encuentra en uso", null);
        }
        return null;
    }

    public ErrorDetailsDto errorIfExistEmail(RegisterDto account){
        if(userService.existsByEmail(account.getUserDto().getEmail())){
            return new ErrorDetailsDto(new Date(), "Email ya se encuentra en uso", null);
        }
        return null;
    }
    public ErrorDetailsDto errorIfExistUniqueKey(RegisterDto account){
        if(serviceTrader.existsByUniqueKey(account.getTraderDto().getCode())){
            return new ErrorDetailsDto(new Date(), "Cuit/Cuil ya se encuentra en uso", null);
        }
        return null;
    }

    public String dataExist(RegisterDto account){
        ErrorDetailsDto comp = this.errorIfExistEmail(account);
        if(StringUtils.hasText(comp.getMensaje())){
            return comp.getMensaje();
        }
        comp = this.errorIfExistUsername(account);
        if(StringUtils.hasText(comp.getMensaje())){
            return comp.getMensaje();
        }
        comp = this.errorIfExistUniqueKey(account);
        if(StringUtils.hasText(comp.getMensaje())){
            return comp.getMensaje();
        }
        return null;
    }

}
