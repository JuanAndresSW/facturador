package dev.facturador.services;

import dev.facturador.dto.LoginDto;
import dev.facturador.entities.Usuarios;
import dev.facturador.repository.IUserRepository;
import dev.facturador.services.abstracciones.IUserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Servicio de Usuario llama a los metodos del Repository
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
     */
    @Override
    public Usuarios getUserWithCrdentials(LoginDto user) {
        Optional<Usuarios> userDta = repository.findByUsernameOrEmail(user.getUsernameOrEmail(), user.getUsernameOrEmail());
        if(!userDta.isEmpty()){
            return userDta.get();
        }
        return null;
    }

    /**
     * Comprueba si existe segun el Username
     */
    @Override
    public boolean existsByUsername(String username){
        return repository.existsByUsername(username);
    }

    /**
     * Comprueba si existe segun el Email
     */
    @Override
    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }
}
