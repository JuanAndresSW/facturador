package dev.facturador.services;

import de.mkammerer.argon2.Argon2Factory;
import dev.facturador.dto.LoginDto;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.CuentaSecundaria;
import dev.facturador.entities.Usuarios;
import dev.facturador.repository.IUserRepository;
import dev.facturador.services.abstracciones.IUserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Servicio del detalle de cuenta
 */
@Service
@Transactional
@NoArgsConstructor
@Slf4j
public class UserService implements IUserService {

    //Injeccion de depencia
    @Autowired
    private IUserRepository repository;
    /**
     * HOF Comprueba que los Hashes sean iguales
     */
    private static final BiFunction<Usuarios, LoginDto, Boolean> thesePasswordsEquals = (x, y) -> Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id)
            .verify(x.getPassword(), y.getPassword().toCharArray(), StandardCharsets.UTF_8);


    /**
     * Regresa el Detalle relacionado con el username
     * @return
     */
    @Override
    public Usuarios getUserByUsername(Usuarios user) {
        Optional<Usuarios> userDta = repository.findByUsername(user.getUsername());
        if(userDta.isEmpty()){
            return null;
        }
        return userDta.get();
    }

    /**
     * Verifica que las credenciales para el Login
     * @return
     */
    @Override
    public Usuarios getUserWithCrdentials(LoginDto user) {
        Optional<Usuarios> userDta = repository.findByUsernameOrEmail(user.getUsernameOrEmail(), user.getUsernameOrEmail());
        if(!userDta.isEmpty()){
            if(thesePasswordsEquals.apply(userDta.get(), user)){
                return userDta.get();
            }
        }
        return null;
    }

    /**
     * Devulve un Usuario para el registro
     * @param user
     * @return
     */
    @Override
    public Usuarios getUserForToken(Usuarios user){
        Optional<Usuarios> userDta=repository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if(!userDta.isEmpty()){
            if(userDta.get().getPassword().equals(user.getPassword())){
                return userDta.get();
            }
        }
        return null;
    }

    /**
     * Obtiene una cuenta principal segun el Username
     */
    @Override
    public CuentaPrincipal getMainAccountPertainToUser(String username){
        return repository.usernamePertainToMainAccount(username);
    }

    /**
     * Obtiene una cuenta secundaria segun el Username
     */
    @Override
    public CuentaSecundaria getSecondaryAccountPertainToUserByUsername(String username){
        return repository.usernamePertainToSecondaryAccount(username);
    }

    /**
     * Comprueba si existe segun el Username
     */
    @Override
    public boolean existsByUsername(String username){
        return repository.existsByUsername(username);
    }
    @Override
    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }
}
