package dev.facturador.global.infrastructure.config;

import dev.facturador.account.infrastructure.AuthenticationFilterForLogin;
import dev.facturador.global.infrastructure.adapters.CustomJWT;
import dev.facturador.global.infrastructure.filters.CustomAuthorizationFilter;
import dev.facturador.global.infrastructure.filters.JWTEntryPoint;
import dev.facturador.global.infrastructure.springservice.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**Clase con la configuracion de Seguridad*/
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
//Indico que uso PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTEntryPoint unauthorizedHandler;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Configura la seguridad de las peticiones Http
     *
     * @param http Parámetro para cofigurar las peticiones
     * @throws Exception Arroja una excepcion generica
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
        //Autorización de las request
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated();

        //Filtros
        http.addFilter(new AuthenticationFilterForLogin(this.authenticationManagerBean(), customJWT()));
        http.addFilterBefore(new CustomAuthorizationFilter(customJWT()), UsernamePasswordAuthenticationFilter.class);
    }

    /**Le indico cual es la clase que busca al usuario y con que enconder se codifica el password*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //Todo lo de abajo solo es para marcar los siguientes como Bean dentro de Spring
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomJWT customJWT() {
        return new CustomJWT();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
    }

}
