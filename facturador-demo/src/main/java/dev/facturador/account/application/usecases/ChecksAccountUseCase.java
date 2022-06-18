package dev.facturador.account.application.usecases;

import dev.facturador.account.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**Caso de uso para claves unicas*/
@Service
public class ChecksAccountUseCase {
    @Autowired
    private AccountRepository repository;
    //Devuelve un mensaje si ya existe una cuenta con este username
    public String errorIfUsernameIsInUse(String username) {
        return Boolean.TRUE.equals(repository.existsByOwnerUserUsername(username)) ? "Nombre de usuario ya se encuentra en uso" : null;
    }
    //Devuelve un mensaje si ya existe una cuena con este CUIT
    public String errorIfCuitIsInUse(String cuit) {
        return Boolean.TRUE.equals(repository.existsByOwnerTraderCuit(cuit)) ? "CUIT ya se encuentra en uso" : null;
    }
    //Devuelve TRUE O FALSE si existe o no una cuenta con este username
    public Boolean checkAccountExistsByUsername(String username) {
        return Boolean.TRUE.equals(repository.existsByOwnerUserUsername(username));
    }
    //Devuelve TRUE O FALSE si existe o no una cuenta con este CUIT
    public Boolean checkAccountExistsByCuit(String cuit) {
        return Boolean.TRUE.equals(repository.existsByOwnerTraderCuit(cuit));
    }
    //Devuelve TRUE O FALSE si existe o no una cuenta con este Email
    public Boolean checkAccountExistsByEmail(String email) {
        return Boolean.TRUE.equals(repository.existsByOwnerUserEmail(email));
    }
    //Comprueba si el CUIT y el Username existen
    public String errorIfCuitOrUsernameIsInUse(String cuit, String username) {
        if (Boolean.TRUE.equals(repository.existsByOwnerTraderCuit(cuit))) {
            if (Boolean.TRUE.equals(repository.existsByOwnerUserUsername(username))) {
                return "Nombre de Usuario y CUIT ya se encuentran en uso";
            }
            return "CUIT ya se encuentra en uso";
        }
        return this.errorIfUsernameIsInUse(username);
    }
    //Comprueba si los 3 están repetidos y retorna un mensaje personalizado segun cada caso
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
