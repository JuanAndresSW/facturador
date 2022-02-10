package dev.facturador.config;


import dev.facturador.jwt.JWTAuthenticationFilter;
import dev.facturador.jwt.JWTEntryPoint;
import dev.facturador.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase de configuracion personalizada pra Spring Security
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Injeccion de depencias
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JWTEntryPoint jwtEntryPoint;

    /**
     * Bean para el Filtro del Token
     */
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

    /**
     * Configuracion para las peticiones http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Desactivo el Cross-site Request Forgery (Eso sirve para evitar falcificacion de formularios)
        //Esto es un ApiRest aqui no hay formularios por eso se desabilita
        http.csrf().disable()
                //Llama en jwtENtryPoint si ve uan excepcion
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()
                //No guardo Cookies, ni sesiones
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //No hago autenticaciones a las peticiones GET echas a api/*
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").permitAll()
                //Compru
                .antMatchers("/api/auth/**").permitAll().anyRequest().authenticated();
        //Se comprueba que el Token sea valido
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //Aqui se encuentran lo necesario para el Authenticate de Spring security
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
}
