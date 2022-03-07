package dev.facturador.usuarios;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import dev.facturador.user.domain.User;
import dev.facturador.user.domain.repository.IUserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private IUserRepository repository;

    @Before
    private User saveForTest(){
        var user = new User("marquitos", "marquitos123", "marquitos@gmail.com");
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        user.setPassword(argon2.encode(user.getPassword()));
        return repository.save(user);
    }

    @Test
    public void findUserInDataBaseByUsername(){
        String username = "marquitos";
        var userByUsername = repository.findByUsername(username);
        assertTrue(userByUsername.isPresent());
        assertThat(userByUsername.get().getUsername()).isEqualTo(username);
    }

    @Test
    public void findUserInDataBaseByUsernameOrEmail(){
        var userByUsername = repository.findByUsernameOrEmail("marquitos@gmail.com", "marquitos@gmail.com");
        assertTrue(userByUsername.isPresent());
        assertNotNull(userByUsername.get());
    }

    @Test
    public void verifyIfExistsUserByUsername(){
        String username = "marquitos";
        assertTrue(repository.existsByUsername(username));
    }

    @Test
    public void verifyIfExistsUserByEmail(){
        String email = "marquitos@gmail.com";
        assertTrue(repository.existsByEmail(email));
    }
}
