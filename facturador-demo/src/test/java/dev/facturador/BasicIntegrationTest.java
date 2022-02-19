package dev.facturador;

import dev.facturador.dto.security.CustomUserDetails;
import dev.facturador.jwt.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = FacturadorMasMasApplication.class)
@SpringBootConfiguration
public class BasicIntegrationTest {
    protected TestRestTemplate restTemplate;
    protected URL base;

    @Autowired
    private JWTUtil provider;
    @Autowired
    private AuthenticationManager authenticationManager;
    private EntityManager entiy;

    @Test
    public void whenLoggedUserRequestHomePage_TheSuccess()
            throws IllegalStateException, IOException {
        this.base = new URL("http", "localhost", 8001, "", null);
        this.restTemplate = new TestRestTemplate("user", "password", TestRestTemplate.HttpClientOption.ENABLE_REDIRECTS);
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void genereteTokenvalid_isValid(){
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("usernaem", "password"));
        var user = (CustomUserDetails) authentication.getPrincipal();
        String token = provider.generateToken(user);

        assertTrue(provider.validateToken(token));
        assertTrue(StringUtils.hasText(token));
        assertEquals(provider.getValue(token), user.getUsername());
    }
}