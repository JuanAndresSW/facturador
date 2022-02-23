package dev.facturador.services.impl;

import dev.facturador.dto.LoginDto;
import dev.facturador.entities.Usuarios;
import dev.facturador.repository.IUserRepository;
import dev.facturador.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * Busca un Usuario segun el username de otro Usuario
     */
    @Override
    public Usuarios getUserByUsername(Usuarios user) {
        Optional<Usuarios> userDta = repository.findByUsername(user.getUsername());
        if (userDta.isEmpty()) {
            return null;
        }
        return userDta.get();
    }

    /**
     * Busca un usuario segun las credenciales pueden ser UsernameOrEmail y la contraseña
     */
    @Override
    public Optional<Usuarios> getUserWithCrdentials(LoginDto user) {
        Optional<Usuarios> userDta = repository.findByUsernameOrEmail(user.usernameOrEmail(), user.usernameOrEmail());
        return userDta;
    }

    /**
     * Comprueba si existe segun el username
     *
     * @param username Nombre de usuario a comprobar si existe
     * @return true si existe false si no
     */
    @Override
    public boolean isExistsUserByUsername(String username) {
        return repository.existsByUsername(username);
    }

    /**
     * Comprueba si existe un usuario con este email
     */
    @Override
    public boolean isExistsUserByEmail(String email) {
        return repository.existsByEmail(email);
    }

}
