package dev.facturador.mainaccount.application;

import dev.facturador.mainaccount.domain.MainAccountRepository;
import dev.facturador.mainaccount.domain.vo.agregate.RegisterRequest;
import dev.facturador.mainaccount.infrastructure.IFactoryMainAccount;
import dev.facturador.mainaccount.infrastructure.service.IMainAccountRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
public class MainAccountRegisterService implements IMainAccountRegisterService {

    @Autowired
    private MainAccountRepository repository;
    @Autowired
    private IFactoryMainAccount factory;

    @Override
    public void register(RegisterRequest tryRegister) {

        var mainAccountLogged = factory.createMainAccountForRegister(tryRegister);
        repository.save(mainAccountLogged);
    }

    @Override
    public Boolean existsByUsernameOfUser(String username) {
        return Boolean.TRUE.equals(repository.existsByUserMainAccountUsername(username));
    }

    @Override
    public Boolean existsByEmailOfUsuarios(String email) {
        return Boolean.TRUE.equals(repository.existsByUserMainAccountEmail(email));
    }

    @Override
    public Boolean existsByUniqueKeyOfTrader(String uniqueKey) {
        return Boolean.TRUE.equals(repository.existsByAccountOwnerUniqueKey(uniqueKey));
    }

    @Override
    public String whenIndicesAreRepeatedReturnErrror(RegisterRequest tryRegister) {
        if(existsByUsernameOfUser(tryRegister.getUserRegister().username())){
            if(existsByEmailOfUsuarios(tryRegister.getUserRegister().email())){
                if(existsByUniqueKeyOfTrader(tryRegister.getTraderRegister().code())){
                    return "Nombre de Usuario, Email y Cuit ya se encuentran en uso";
                }

                return "Nombre de Usuario y Email ya se encuentran en uso";
            }
            if(existsByUniqueKeyOfTrader(tryRegister.getTraderRegister().code())){
                return "Nombre de Usuario y Cuit ya se encuentran en uso";
            }

            return "Nombre de usuario ya se encuentra en uno";
        }

        if(existsByEmailOfUsuarios(tryRegister.getUserRegister().email())){
            if(existsByUniqueKeyOfTrader(tryRegister.getTraderRegister().code())){
                return "Email y Cuit ya se encuentran en uso";
            }
            return "Email ya se encuentran en uso";
        }

        if(existsByUniqueKeyOfTrader(tryRegister.getTraderRegister().code())){
            return "Cuit ya se encuentra en uso";
        }

        return null;
    }
}
