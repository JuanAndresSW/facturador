package dev.facturador.config;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;


@Configuration
public class ValidationConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        var postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator());
        return postProcessor;
    }

    @Bean
    public Validator validator(){
        return  Validation.byDefaultProvider().configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory()
                .getValidator();
    }
}
