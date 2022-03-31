package dev.facturador.mainaccount.application.usecases;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.MainAccountRegister;
import dev.facturador.mainaccount.domain.MainAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterMainAccountUseCase {

    private MainAccountRepository repository;

    public void handle(MainAccountRegister request) {
        var mainAccountLogged = MainAccount.create(request);
        repository.save(mainAccountLogged);
    }

    public Boolean existsByUsernameOfUser(String username) {
        return Boolean.TRUE.equals(repository.existsByUserMainAccountUsername(username));
    }

    public Boolean existsByEmailOfUsuarios(String email) {
        return Boolean.TRUE.equals(repository.existsByUserMainAccountEmail(email));
    }

    public Boolean existsByUniqueKeyOfTrader(String uniqueKey) {
        return Boolean.TRUE.equals(repository.existsByAccountOwnerUniqueKey(uniqueKey));
    }

    public String whenIndicesAreRepeatedReturnErrror(MainAccountRegister userForRegister) {
        if (this.existsByUsernameOfUser(userForRegister.userRegister().username())) {
            if (this.existsByEmailOfUsuarios(userForRegister.userRegister().email())) {
                if (this.existsByUniqueKeyOfTrader(userForRegister.traderRegister().code())) {
                    return "Nombre de Usuario, Email y Cuit ya se encuentran en uso";
                }

                return "Nombre de Usuario y Email ya se encuentran en uso";
            }
            if (this.existsByUniqueKeyOfTrader(userForRegister.traderRegister().code())) {
                return "Nombre de Usuario y Cuit ya se encuentran en uso";
            }

            return "Nombre de usuario ya se encuentra en uno";
        }

        if (this.existsByEmailOfUsuarios(userForRegister.userRegister().email())) {
            if (this.existsByUniqueKeyOfTrader(userForRegister.traderRegister().code())) {
                return "Email y Cuit ya se encuentran en uso";
            }
            return "Email ya se encuentran en uso";
        }

        if (this.existsByUniqueKeyOfTrader(userForRegister.traderRegister().code())) {
            return "Cuit ya se encuentra en uso";
        }

        return null;
    }
}
