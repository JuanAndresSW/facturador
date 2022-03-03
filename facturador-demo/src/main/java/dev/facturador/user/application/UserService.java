package dev.facturador.user.application;

import dev.facturador.user.domain.Usuarios;
import dev.facturador.user.domain.repository.IUserRepository;
import dev.facturador.user.infrastructure.IUserService;
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
    public Optional<Usuarios> getUserWithCrdentials(String usernameOrEmail) {
        return repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }

    /**
     * Comprueba si existe segun el username
     */
    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    /**
     * Comprueba si existe un usuario con este email
     */
    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

}
