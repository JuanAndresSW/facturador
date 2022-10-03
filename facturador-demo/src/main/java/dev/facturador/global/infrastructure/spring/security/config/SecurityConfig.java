package dev.facturador.global.infrastructure.spring.security.config;

import dev.facturador.account.infrastructure.AuthenticationFilterForLogin;
import dev.facturador.global.domain.CustomUserDetails;
import dev.facturador.global.infrastructure.adapters.CustomJWT;
import dev.facturador.global.infrastructure.spring.security.CustomUserDetailsService;
import dev.facturador.global.infrastructure.spring.security.filter.CustomAuthorizationFilter;
import dev.facturador.global.infrastructure.spring.security.filter.JWTEntryPoint;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase con la configuracion de Seguridad
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
//Indico que uso PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTEntryPoint unauthorizedHandler;
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
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();

        //Filtros
        http.addFilter(new AuthenticationFilterForLogin(this.authenticationManagerBean(), customJWT()));
        http.addFilterBefore(new CustomAuthorizationFilter(customJWT()), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Le indico cual es la clase que busca al usuario y con que enconder se codifica el password
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    //Apartir de aqui solo hay Beans
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
