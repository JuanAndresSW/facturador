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
    public Optional<String> existsByUsernameOfUser(String username) {
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

    public Collection<String> whenIndicesAreRepeatedReturnErrror(RegisterRequest account) {
        Collection<String> errorMessage = new HashSet<>();

        Optional<String> messageWhenRepeated = existsByEmailOfUsuarios(account.getUserRegister().email());
        if (messageWhenRepeated.isPresent()) {
            errorMessage.add(messageWhenRepeated.get());
            messageWhenRepeated = Optional.empty();
        }
        messageWhenRepeated = existsByUsernameOfUser(account.getUserRegister().username());
        if (messageWhenRepeated.isPresent()) {
            errorMessage.add(messageWhenRepeated.get());
            messageWhenRepeated = Optional.empty();
        }
        messageWhenRepeated = existsByUniqueKeyOfTrader(account.getTraderRegister().code());
        if (messageWhenRepeated.isPresent()) {
            errorMessage.add(messageWhenRepeated.get());
            messageWhenRepeated = Optional.empty();
        }
        return errorMessage;
    }
}
