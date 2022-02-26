package dev.facturador;

import dev.facturador.bo.LoginBo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = FacturadorMasMasApplication.class)
@SpringBootConfiguration
public class BasicIntegrationTest {
    protected TestRestTemplate restTemplate;
    protected URL base;


    @Test
    public void whenLoggedUserRequestHomePage_TheSuccess() {
        LoginBo loginUser = null;
    }


}