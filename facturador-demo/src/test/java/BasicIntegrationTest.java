import dev.facturador.FacturadorMasMasApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = FacturadorMasMasApplication.class)
@SpringBootConfiguration
public class BasicIntegrationTest {
    protected TestRestTemplate restTemplate;
    protected URL base;

    @Test
    public void whenLoggedUserRequestHomePage_TheSuccess()
            throws IllegalStateException, IOException {
        this.base = new URL("http", "localhost", 8080, "", null);
        this.restTemplate = new TestRestTemplate("user", "password", TestRestTemplate.HttpClientOption.ENABLE_REDIRECTS);
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}