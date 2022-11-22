package dev.facturador;

import java.io.IOException;
import java.security.GeneralSecurityException;

import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
public class TestUpload {
    @Autowired
    private ReactiveRequest<Class, Void> reactiveRequest;

    public void testUpload() throws GeneralSecurityException, IOException {

/*
        var request = reactiveRequest.makeRequest(
                "POST",
                "/api/user",
                BodyInserters.fromFormData(values),
                MediaType.APPLICATION_JSON,
                Void.class,
                null,
                null);
         */
    }


}
