package dev.facturador.mainaccount.application;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.MainAccountRepository;
import dev.facturador.mainaccount.domain.vo.agregate.UpdateRequest;
import dev.facturador.mainaccount.infrastructure.IFactoryMainAccount;
import dev.facturador.mainaccount.infrastructure.service.IMainAccountUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
@Transactional
public class MainAccountUpdateService implements IMainAccountUpdateService {

    @Autowired
    private MainAccountRepository repository;
    @Autowired
    private IFactoryMainAccount factory;

    @Override
    public void update(UpdateRequest tryUpdate, MainAccount account) {
        var user = factory.createMainAccountForUpdate(tryUpdate, account);
        repository.saveAndFlush(user);
    }

    @Override
    public MainAccount getMainAccountByUsername(String username){
        var user =repository.findByUserMainAccountUsername(username);
        return user.orElse(null);
    }

    @Override
    public Boolean existsByUsernameOfUser(String username) {
        return Boolean.TRUE.equals(repository.existsByUserMainAccountUsername(username));
    }

    @Override
    public Boolean existsByUniqueKeyOfTrader(String uniqueKey) {
        return Boolean.TRUE.equals(repository.existsByAccountOwnerUniqueKey(uniqueKey));
    }
    @Override
    public String verifyUsernameNotExists(UpdateRequest tryUpdate){
        if(existsByUsernameOfUser(tryUpdate.getUser().newUsername())){
            if(existsByUniqueKeyOfTrader(tryUpdate.getTrader().newCode())){
                return "Nombre de Usuario y Cuit ya se encuentran en uso";
            }
            return "Nombre de Usuario ya se encuentra en uso";
        }
        return null;
    }
    @Override
    public String verifyCuitNotExists(UpdateRequest tryUpdate){
        if(existsByUniqueKeyOfTrader(tryUpdate.getTrader().newCode())){
            if(existsByUsernameOfUser(tryUpdate.getUser().newUsername())){
                return "Nombre de Usuario y Cuit ya se encuentran en uso";
            }
            return "Cuit ya se encuentra en uso";
        }
        return null;
    }
    @Override
    public String verifyUsernameAndCodeNotExists(UpdateRequest tryUpdate){
        String message = null;
        if(StringUtils.hasText(tryUpdate.getUser().newUsername())){
            message = verifyUsernameNotExists(tryUpdate);
        }
        if(!StringUtils.hasText(message)){
            if(StringUtils.hasText(tryUpdate.getTrader().newCode())){
                message =verifyCuitNotExists(tryUpdate);
            }
        }
        return message;
    }


    @Override
    public String verifyIfCotainsNewPassword(UpdateRequest tryUpdate, MainAccount user){
        String newPassword = tryUpdate.getUser().newPassword();
        if(StringUtils.hasText(newPassword)){
            var argon = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
            String passwordEncoded = user.getUserMainAccount().getPassword();
            String password = tryUpdate.getUser().password();

            if(!argon.matches(password, passwordEncoded)){
                return "La contraseña antigua es incorrecta";
            }
        }
        return null;
    }

    @Override
    public String verifyData(UpdateRequest data, MainAccount user){
        String message = null;
        message = verifyIfCotainsNewPassword(data, user);
        if(StringUtils.hasText(message)){
            return message;
        }
        message = verifyUsernameAndCodeNotExists(data);
        if(StringUtils.hasText(message)){
            return message;
        }
        message = verifyNameAndCategoryAreDifferent(data, user);
        if(StringUtils.hasText(message)){
            return message;
        }
        return null;
    }

    @Override
    public String verifyNameAndCategoryAreDifferent(UpdateRequest data, MainAccount user) {
        if (StringUtils.hasText(data.getTrader().businessName())) {
            String actualName = user.getAccountOwner().getName();
            if(actualName.equals(data.getTrader().businessName())) {
                return "La informacion debe de ser distinta a la actual para actualizarla";
            }
        }
        if (StringUtils.hasText(data.getTrader().vatCategory())) {
            String actualVat = user.getAccountOwner().getVat().name();
            String newVat = data.getTrader().vatCategory();
            if (actualVat.toLowerCase().equals(newVat.toLowerCase())) {
                return "La informacion debe de ser distinta a la actual para actualizarla";
            }
        }
        return null;
    }
}
