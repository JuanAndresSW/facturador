package dev.facturador.mainaccount.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter @NoArgsConstructor @AllArgsConstructor
public class MainAccountIdUsername {
    @Length(min = 3, max = 20)
    private String username;

    public static MainAccountIdUsername starter(String username) {
        return new MainAccountIdUsername(username);
    }
}
