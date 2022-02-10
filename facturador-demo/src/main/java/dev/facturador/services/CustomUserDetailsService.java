package dev.facturador.services;

import dev.facturador.dto.security.CustomUserDetails;
import dev.facturador.entities.Usuarios;
import dev.facturador.services.abstracciones.IMainAccountService;
import dev.facturador.services.abstracciones.ISecondaryAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static dev.facturador.dto.security.CustomUserDetails.createUser;

/**
 * Servicio personalizado de UserDetails(Servicio de Spring Security)
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IMainAccountService serviceMain;
    @Autowired
    private ISecondaryAccountService serviceSecondary;

    /**
     * Este metodo carga un Usuario personalizado de spring security
     * Le Otorga el Rol de MAIN o SECONDARY
     * Para definir el ROL consulta las dos tablas (principal y secundaria)
     * Si las dos devulven null se genera un excepcion
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Revisa primero si es MAIN
        var userMainExit= serviceMain.getMainAccountByUsername(username);
        if(userMainExit != null){
            return this.userBuilder(userMainExit.getUserMainAccount(), "MAIN");
        }
        //Si no es MAIN entonces es secundaria
        var userSecondExit = serviceSecondary.getSecondaryAccountByUsername(username);
        if(userSecondExit != null){
            return this.userBuilder(userSecondExit.getUserSecondaryAccount(), "SECONDARY");
        }
        //Si ambas dan null
        throw new UsernameNotFoundException("Username dow not exist");
    }

    /**
     * Construye el UserDetails personalizado
     */
    private UserDetails userBuilder(Usuarios user, String rol){
        List<GrantedAuthority> authorities = new ArrayList<>(2);
        authorities.add(new SimpleGrantedAuthority("ROLE_"+rol));

        return new CustomUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(), authorities);
    }
}
