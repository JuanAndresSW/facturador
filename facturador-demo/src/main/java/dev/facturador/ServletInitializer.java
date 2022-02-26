package dev.facturador;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Indica donde se va iniciar el spring boot application
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FacturadorMasMasApplication.class);
    }

}
