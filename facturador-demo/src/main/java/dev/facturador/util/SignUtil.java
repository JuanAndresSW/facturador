package dev.facturador.util;

import dev.facturador.dto.RegisterDto;
import dev.facturador.dto.response.LoginResponse;
import dev.facturador.services.ITraderService;
import dev.facturador.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static dev.facturador.util.VerifyIfExists.*;
import static java.lang.Integer.parseInt;

@Component
@RequiredArgsConstructor
public final class SignUtil {
    public static Map<String, String> getDataFromHeader(HttpHeaders headers){
        var values = new HashMap<String, String>();
        values.put("access", headers.get("Access-token").get(0));
        values.put("refresh", headers.get("Refresh-token").get(0));

        values.put("username", headers.get("user-data").get(0));
        values.put("rol", headers.get("user-data").get(1));

        return values;

    }

    public static LoginResponse createLoginResponse(HttpHeaders headers, Map<String, String> data) {
        if(data.get("rol").equals("MAIN")){
            String active = headers.get("user-data").get(2);
            String passive = headers.get("user-data").get(3);
            if(StringUtils.hasText(active) && StringUtils.hasText(passive)){
                return new LoginResponse(data.get("username"), parseInt(active), parseInt(passive), data.get("access"), data.get("refresh"));
            }
        }
        return new LoginResponse(data.get("username"), data.get("access"), data.get("refresh"));
    }

    /**
     * Invoca a los me
     */
    public static String whetherExistsReturnMessage(RegisterDto account, IUserService userService, ITraderService traderService){
        Optional<String> comp = errorIfExistEmail(account, userService);
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = errorIfExistUsername(account,userService);
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = errorIfExistUniqueKey(account, traderService);
        return comp.orElse(null);
    }
}
