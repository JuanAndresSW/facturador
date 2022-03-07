package dev.facturador.mainaccount.application;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.IMainAccountRepository;
import dev.facturador.mainaccount.domain.bo.RegisterRequest;
import dev.facturador.mainaccount.infrastructure.IMainAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static dev.facturador.mainaccount.domain.MainAccount.createMainAccountForRegister;

@Service
@Transactional
public class MainAccountService implements IMainAccountService {

    @Autowired
    private IMainAccountRepository repository;

    /**
     * Guarda en la base de datos
     *
     * @param tryRegister {@link RegisterRequest} Es la entidad
     */
    @Override
    public void register(RegisterRequest tryRegister) {
        var mainAccountLogged = createMainAccountForRegister(tryRegister);
        repository.save(mainAccountLogged);
    }

    @Override
    public Optional<String> existsByUsernameOfUsuarios(String username) {
        if (Boolean.TRUE.equals(repository.existsByUserMainAccountUsername(username))) {
            return Optional.of("Nombre de usuario ya se encuentra en uso");
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> existsByEmailOfUsuarios(String email) {
        if (Boolean.TRUE.equals(repository.existsByUserMainAccountEmail(email))) {
            return Optional.of("Email ya se encuentra en uso");
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

    public String whenIndicesAreRepeatedReturnErrror(RegisterRequest account) {
        Optional<String> comp = existsByEmailOfUsuarios(account.getUserBo().email());
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = existsByUsernameOfUsuarios(account.getUserBo().username());
        if (comp.isPresent()) {
            return comp.get();
        }
        comp = existsByUniqueKeyOfTrader(account.getTraderBo().code());
        return comp.orElse(null);
    }
}
