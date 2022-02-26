package dev.facturador.util;

import dev.facturador.dto.response.LoginResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public final class SignInUtil {
    public static Map<String, String> getDataFromHeader(HttpHeaders headers) {
        var values = new HashMap<String, String>();
        values.put("access", headers.get("Access-token").get(0));
        values.put("refresh", headers.get("Refresh-token").get(0));

        values.put("username", headers.get("user-data").get(0));
        values.put("rol", headers.get("user-data").get(1));

        return values;

    }

    public static LoginResponse createLoginResponse(HttpHeaders headers, Map<String, String> data) {
        if (data.get("rol").equals("MAIN")) {
            String active = headers.get("user-data").get(2);
            String passive = headers.get("user-data").get(3);
            if (StringUtils.hasText(active) && StringUtils.hasText(passive)) {
                return new LoginResponse(data.get("username"), parseInt(active), parseInt(passive), data.get("access"), data.get("refresh"));
            }
        }
        return new LoginResponse(data.get("username"), data.get("access"), data.get("refresh"));
    }
}
