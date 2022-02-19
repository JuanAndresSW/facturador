package dev.facturador.config;


import dev.facturador.dto.security.UsernamePasswordWithTimeoutAuthenticationFilter;
import dev.facturador.jwt.JWTAuthenticationFilter;
import dev.facturador.jwt.JWTEntryPoint;
import dev.facturador.services.impl.CustomUserDetailsService;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTEntryPoint jwtEntryPoint;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/webjars/**");
    }


    /**
     * Configura la seguridad de las peticiones Http
     * @param http Paramnetro para cofigurar las peticiones
     * @throws Exception puede arroajr esta excepcion
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Desactivo el Cross-site Request Forgery (Eso sirve para evitar falcificacion de formularios)
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()
                //No guardo Cookies, ni sesiones
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                //Todas las demas request se autentican (Por ahora...)
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordWithTimeoutAuthenticationFilter.class);
    }


    /**
     * Indico la instancia de UserDetails que va a manejar el AuthenticationManagerBuilder
     * tambien indico cual es el encriptador de contraseñas que estoy usando
     * @param auth es el AuthenticationManagerBuilder a cofigurar
     * @throws Exception por si envia alguna excepcion
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
    }

}
