package dev.facturador.security.infrastructure.config;

import dev.facturador.account.infrastructure.AuthenticationFilterForLogin;
import dev.facturador.security.infrastructure.adapter.CustomJWT;
import dev.facturador.security.infrastructure.adapter.CustomUserDetailsService;
import dev.facturador.security.infrastructure.filter.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase con la configuracion de Seguridad
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //Desactivo el Cross-site request forgery no es necesario
            .csrf().disable()
                //Desactivo el http-basic no es necesario
            .httpBasic().disable()
                //Marco una politica de no sesiones
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //Le digo que es lo que tiene que hacer el entry-point
        http.exceptionHandling().authenticationEntryPoint(
                (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) ->
                            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                         "Sorry, You're not authorized to access this resource."));

        //Autorización de las request
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/", "/favicon.ico",
                        "/include/**",
                        "/layer/**",
                        "/index",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js").permitAll()
                .anyRequest().authenticated();
        //Provedor de autenticacion personalizado
        http.authenticationProvider(authenticationProvider());

        //Filtros
        http.addFilter(new AuthenticationFilterForLogin(this.authenticationManager(), customJWT()));
        http.addFilterBefore(new CustomAuthorizationFilter(customJWT(), userDetailsService), UsernamePasswordAuthenticationFilter.class);
        //Configuracion de CORS
        http.cors().configurationSource( new CorsConfig().corsConfigurationSource());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    //Apartir de aqui solo hay Beans
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public ServerSecurityContextRepository serverSecurityContextRepository() {
        return new WebSessionServerSecurityContextRepository();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
    }

    @Bean
    public CustomJWT customJWT() {
        return new CustomJWT();
    }



}
