package dev.facturador.security.infrastructure.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {
}
