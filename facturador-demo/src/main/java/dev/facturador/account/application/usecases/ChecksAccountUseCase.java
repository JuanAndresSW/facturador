package dev.facturador.account.application.usecases;

import dev.facturador.account.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChecksAccountUseCase {
    @Autowired
    private AccountRepository repository;

    public String errorIfUsernameIsInUse(String username) {
        return Boolean.TRUE.equals(repository.existsByOwnerUserUsername(username)) ? "Nombre de usuario ya se encuentra en uso" : null;
    }

    public String errorIfCuitIsInUse(String cuit) {
        return Boolean.TRUE.equals(repository.existsByOwnerTraderCuit(cuit)) ? "CUIT ya se encuentra en uso" : null;
    }

    public Boolean checkAccountExistsByUsername(String username) {
        return Boolean.TRUE.equals(repository.existsByOwnerUserUsername(username));
    }

    public Boolean checkAccountExistsByCuit(String cuit) {
        return Boolean.TRUE.equals(repository.existsByOwnerTraderCuit(cuit));
    }

    public Boolean checkAccountExistsByEmail(String email) {
        return Boolean.TRUE.equals(repository.existsByOwnerUserEmail(email));
    }

    public String errorIfCuitOrUsernameIsInUse(String cuit, String username) {
        if (Boolean.TRUE.equals(repository.existsByOwnerTraderCuit(cuit))) {
            if (Boolean.TRUE.equals(repository.existsByOwnerUserUsername(username))) {
                return "Nombre de Usuario y CUIT ya se encuentran en uso";
            }
            return "CUIT ya se encuentra en uso";
        }
        return this.errorIfUsernameIsInUse(username);
    }

    public String errorIfAnyIndexIsInUse(String username, String email, String cuit) {
        if (Boolean.TRUE.equals(repository.existsByOwnerUserEmail(email))) {
            if (Boolean.TRUE.equals(repository.existsByOwnerUserUsername(username))) {
                if (Boolean.TRUE.equals(repository.existsByOwnerTraderCuit(cuit))) {
                    return "Nombre de Usuario, CUIT y Correo Electronico ya se encuentran en uso";
                }
                return "Nombre de Usuario y Correo Electronico ya se encuentran en uso";
            }
            if (Boolean.TRUE.equals(repository.existsByOwnerTraderCuit(cuit))) {
                return "CUIT y Correo Electronico ya se encuentran en uso";
            }

            return "Correo Electronico ya se encuentra en uno";
        }

        return this.errorIfCuitOrUsernameIsInUse(cuit, username);
    }

}
