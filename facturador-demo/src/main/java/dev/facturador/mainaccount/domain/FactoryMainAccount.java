package dev.facturador.mainaccount.domain;

import dev.facturador.mainaccount.domain.vo.agregate.RegisterRequest;
import dev.facturador.mainaccount.domain.vo.agregate.UpdateRequest;
import dev.facturador.mainaccount.infrastructure.IFactoryMainAccount;
import dev.facturador.shared.domain.Vat;
import dev.facturador.trader.domain.Trader;
import dev.facturador.user.domain.User;
import dev.facturador.user.domain.UserAvatar;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class FactoryMainAccount implements IFactoryMainAccount {
    @Override
    public MainAccount createMainAccountForRegister(RegisterRequest tryRegister) {
        var account = new MainAccount();
        account.setCreateAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        account.setAccountOwner(new Trader(tryRegister.getTraderRegister().code(), tryRegister.getTraderRegister().grossIncome(), tryRegister.getTraderRegister().businessName(), 0, 0));

        String vatName = tryRegister.getTraderRegister().vatCategory();
        if (vatName.contains("Responsable")) {
            account.getAccountOwner().setVat(Vat.RESPONSABLE_INSCRIPTO);
        }
        if (vatName.contains("Mono")) {
            account.getAccountOwner().setVat(Vat.MONOTRIBUTISTA);
        }
        if (vatName.contains("Sujeto")) {
            account.getAccountOwner().setVat(Vat.SUJETO_EXENTO);
        }
        String passwordHash = hashPassword(tryRegister.getUserRegister().password());
        account.setUserMainAccount(new User
                (tryRegister.getUserRegister().username(), passwordHash, tryRegister.getUserRegister().email()));

        if (StringUtils.hasText(tryRegister.getUserRegister().avatar())) {
            account.getUserMainAccount().setAvatarUser(new UserAvatar(tryRegister.getUserRegister().avatar(), account.getUserMainAccount()));
        }

        return account;
    }

    @Override
    public String hashPassword(String password) {
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        return argon2.encode(password);
    }

    @Override
    public MainAccount createMainAccountForUpdate(UpdateRequest request, MainAccount account){
        if(StringUtils.hasText(request.getUser().newUsername())){
            account.getUserMainAccount().setUsername(request.getUser().newUsername());
        }
        if(StringUtils.hasText(request.getUser().newPassword())){
            String passwordHash = hashPassword(request.getUser().newPassword());
            account.getUserMainAccount().setPassword(passwordHash);
        }
        if(StringUtils.hasText(request.getUser().newAvatar())){
            account.getUserMainAccount().getAvatarUser().setAvatar(request.getUser().newAvatar());
        }
        if(StringUtils.hasText(request.getTrader().newBusinessName())){
            account.getAccountOwner().setName(request.getTrader().newBusinessName());
        }
        if(StringUtils.hasText(request.getTrader().newCode())){
            account.getAccountOwner().setUniqueKey(request.getTrader().newCode());
        }
        if(StringUtils.hasText(request.getTrader().newVatCategory())){
            String vatName = request.getTrader().newVatCategory();
            if (vatName.contains("Responsable")) {
                account.getAccountOwner().setVat(Vat.RESPONSABLE_INSCRIPTO);
            }
            if (vatName.contains("Mono")) {
                account.getAccountOwner().setVat(Vat.MONOTRIBUTISTA);
            }
            if (vatName.contains("Sujeto")) {
                account.getAccountOwner().setVat(Vat.SUJETO_EXENTO);
            }
        }
        return account;
    }
}
