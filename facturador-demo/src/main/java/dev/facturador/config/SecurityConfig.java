package dev.facturador.config;

import dev.facturador.jwt.JWTAuthenticationFilter;
import dev.facturador.jwt.JWTEntryPoint;
import dev.facturador.services.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JWTEntryPoint jwtEntryPoint;

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Desactivo el Cross-site Request Forgery (Eso sirve para evitar falcificacion de formularios)
        //Esto es un ApiRest aqui no hay formularios por eso se desabilita
        http.csrf().disable()
                //Llama en jwtENtryPoint si ve uan excepcion
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint).and()
                //No guardo Cookies, ni sesiones
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //Permite todas las request Get echas a /api/**
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").permitAll()
                //Autentica todas las request * echas a /api/auth/**
                .antMatchers("/api/auth/**").permitAll().anyRequest().authenticated();
        //Con este filtro en cada peticion se comprueba que el token sea valido
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
