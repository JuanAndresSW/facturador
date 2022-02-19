package dev.facturador.services.impl;

import dev.facturador.dto.ErrorDetailsDto;
import dev.facturador.dto.LoginDto;
import dev.facturador.dto.RegisterDto;
import dev.facturador.entities.Usuarios;
import dev.facturador.repository.IUserRepository;
import dev.facturador.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * Servicio de Usuario llama a los metodos del Repository
 */
@Service
@Transactional
public class UserService implements IUserService {

    //Injeccion de depencia
    @Autowired
    private IUserRepository repository;

    /**
     * Busca un Usuario segun el username de otro
     * @param user Usuario del que se sacara el username
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
     * Retorna un usuario segun las credenciales indice (username o email cualquiera)
     * @param user Dto del login con un username o email
     * @return
     */
    @Override
    public Optional<Usuarios> getUserWithCrdentials(LoginDto user) {
        Optional<Usuarios> userDta = repository.findByUsernameOrEmail(user.getUsernameOrEmail(), user.getUsernameOrEmail());
        return userDta;
    }

    /**
     * Comprueba si existe segun el username
     * @param username Nombre de usuario a comprobar si existe
     * @return true si existe false si no
     */
    @Override
    public boolean existsByUsername(String username){
        return repository.existsByUsername(username);
    }

    /**
     * Comprueba si existe segun el email
     * @param email email proporcionado
     * @return true si existe false si no
     */
    @Override
    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }

}
