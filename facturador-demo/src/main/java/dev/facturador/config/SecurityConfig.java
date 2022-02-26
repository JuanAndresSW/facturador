package dev.facturador.config;

import dev.facturador.filter.CustomAuthenticationFilter;
import dev.facturador.filter.CustomAuthorizationFilter;
import dev.facturador.services.impl.CustomUserDetailsService;
import dev.facturador.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Configura la seguridad de las peticiones Http
     *
     * @param http Paramnetro para cofigurar las peticiones
     * @throws Exception puede arroajr esta excepcion
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Desactivo el crsf y las sesiones agrego la politica de Session Stateless
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //Autorizacion de las request
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/auth/refresh").permitAll()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/auth/mainaccounts").permitAll()
                .anyRequest().authenticated();

        //Filtro de Autenticacion esta presenta pero lo que nos importa se llama solo en el login
        http.addFilter(new CustomAuthenticationFilter(this.authenticationManagerBean(), jwtUtil()));
        //Se llame al filtro de autorizacion antes de todas las request
        http.addFilterBefore(new CustomAuthorizationFilter(jwtUtil()), UsernamePasswordAuthenticationFilter.class);
    }

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
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
    }

<<<<<<< HEAD
=======
    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }

>>>>>>> 25a016802cd666b6a85a59aa0e1b8c1741d89337
}
