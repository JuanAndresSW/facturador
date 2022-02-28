package dev.facturador.mainaccount.application;

import dev.facturador.mainaccount.domain.CuentaPrincipal;
import dev.facturador.mainaccount.domain.IMainAccountRepository;
import dev.facturador.mainaccount.domain.bo.RegisterBo;
import dev.facturador.mainaccount.infrastructure.IMainAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static dev.facturador.mainaccount.domain.CuentaPrincipal.createMainAccountForRegister;

@Service
@Transactional
public class MainAccountService implements IMainAccountService {

    @Autowired
    private IMainAccountRepository repository;

    /**
     * Guarda en la base de datos
     *
     * @param tryRegister {@link RegisterBo} Es la entidad
     */
    @Override
    public void register(RegisterBo tryRegister) {
        var mainAccountLogged = createMainAccountForRegister(tryRegister);
        repository.save(mainAccountLogged);
    }

    /**
     * Recupera una {@link CuentaPrincipal} con el username proporcionado <br/>
     * Utilizando la clase {@link Optional} para asegurar los {@link NullPointerException}
     *
     * @param username Referencia para encontrar la {@link CuentaPrincipal}
     * @return devuelve un {@link Optional} de {@link CuentaPrincipal}
     */
    @Override
    public CuentaPrincipal getMainAccountByUsername(String username) {
        Optional<CuentaPrincipal> mainAccount = repository.findByUsername(username);
        if (mainAccount.isEmpty()) {
            return null;
        }
        return mainAccount.get();
    }

    @Override
    public Optional<String> existsByUsernameOfUsuarios(String username) {
        if (repository.existsByUserMainAccountUsername(username)) {
            return Optional.of("Nombre de usuario ya se encuentra en uso");
        }
        return Optional.empty();
    }

    @Override
    public Optional<String>  existsByEmailOfUsuarios(String email) {
        if (repository.existsByUserMainAccountEmail(email)) {
            return Optional.of("Email ya se encuentra en uso");
        }
        return Optional.empty();
    }

    @Override
    public Optional<String>  existsByUniqueKeyOfTrader(String uniqueKey) {
        if (repository.existsByAccountOwnerUniqueKey(uniqueKey)) {
            return Optional.of("Cuit/Cuil ya se encuentra en uso");
        }
        return Optional.empty();
    }

    public String whenIndicesAreRepeatedReturnErrror(RegisterBo account) {
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
