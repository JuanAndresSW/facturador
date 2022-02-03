package dev.facturador.util;

import dev.facturador.entities.Comerciante;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.DetallesCuenta;
import dev.facturador.dto.Account;
import org.springframework.stereotype.Component;

@Component
public final class JSONTranslator {

    /**
     * Transforma el dto Account a la entidad CuentaPrincipal
     */
    public static CuentaPrincipal translatorAccountToMainAccount(Account account){
        CuentaPrincipal mainAccount = new CuentaPrincipal();
        mainAccount.setAccountOwner(new Comerciante
                (account.getTrader().getCode(), account.getTrader().getVatCategory(), account.getTrader().getBusinessName()));

        mainAccount.setMainAccountDetails(new DetallesCuenta
                (account.getUser().getUsername(), account.getUser().getPassword(), account.getUser().getEmail()));

        return mainAccount;
    }
}
