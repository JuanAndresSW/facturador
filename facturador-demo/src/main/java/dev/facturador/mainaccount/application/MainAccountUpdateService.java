package dev.facturador.mainaccount.application;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.MainAccountRepository;
import dev.facturador.mainaccount.domain.vo.agregate.UpdateRequest;
import dev.facturador.mainaccount.infrastructure.IFactoryMainAccount;
import dev.facturador.mainaccount.infrastructure.IMainAccountUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

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
    public Optional<String> existsByUsernameOfUser(String username) {
        if (Boolean.TRUE.equals(repository.existsByUserMainAccountUsername(username))) {
            return Optional.of("Nombre de usuario ya se encuentra en uso");
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> existsByUniqueKeyOfTrader(String uniqueKey) {
        if (Boolean.TRUE.equals(repository.existsByAccountOwnerUniqueKey(uniqueKey))) {
            return Optional.of("Cuit/Cuil ya se encuentra en uso");
        }
        return Optional.empty();
    }
    @Override
    public Collection<String> verifyUsernameAndCodeNotExists(UpdateRequest tryUpdate){
        Collection<String> errorMessage = new HashSet<>();
        Optional<String> messageWhenRepeated = existsByUsernameOfUser(tryUpdate.getUser().newUsername());
        if (messageWhenRepeated.isPresent()) {
            errorMessage.add(messageWhenRepeated.get());
            messageWhenRepeated = Optional.empty();
        }
        messageWhenRepeated = existsByUniqueKeyOfTrader(tryUpdate.getTrader().newCode());
        if (messageWhenRepeated.isPresent()) {
            errorMessage.add(messageWhenRepeated.get());
            messageWhenRepeated = Optional.empty();
        }

        return errorMessage;
    }

    @Override
    public MainAccount getMainAccountByUsername(String username){
        var user =repository.findByUserMainAccountUsername(username);
        return user.orElse(null);
    }

    @Override
    public MainAccount verifyIfCotainsNewPassword(UpdateRequest tryUpdate){
        var user = getMainAccountByUsername(tryUpdate.getUser().username());
        String newPassword = tryUpdate.getUser().newPassword();
        if(StringUtils.hasText(newPassword)){
            var decicion = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
            String passwordEncoded = user.getUserMainAccount().getPassword();
            String password = tryUpdate.getUser().password();
            if(!decicion.matches(password, passwordEncoded)){
                return null;
            }
        }
        return user;
    }
}
