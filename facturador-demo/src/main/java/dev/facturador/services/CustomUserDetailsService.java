package dev.facturador.services;

import dev.facturador.services.abstracciones.IMainAccountService;
import dev.facturador.services.abstracciones.ISecondaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IMainAccountService serviceMain;
    @Autowired
    private ISecondaryAccountService serviceSecondary;

    /**
     * Este metodo carga un Usuario personalizado de spring security
     * Le Otorga el Rol de MAIN o SECONDARY
     * Para saber cual es hace una consutla a las dos tablas y la que no sea nula es porque ahi esp
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Busca en las dos tablas
        var userMainExit= serviceMain.getMainAccountByUsername(username);
        var userSecondExit = serviceSecondary.getSecondaryAccountByUsername(username);
        //Retorna segun el caso
        if(userMainExit == null && userSecondExit == null){
            throw new UsernameNotFoundException("Username dow not exist");
        }
        if(userMainExit != null){
            return this.userBuilder(userMainExit.getUserMainAccount().getUsername(), userMainExit.getUserMainAccount().getPassword(), "MAIN");
        }
        return this.userBuilder(userSecondExit.getUserSecondaryAccount().getUsername(), userSecondExit.getUserSecondaryAccount().getPassword(), "SECONDARY");
    }

    /**
     * Construye el usuario de Spring
     */
    private UserDetails userBuilder(String username, String password, String rol){
        //Ya que nosotros no manejamos estos datos le doy true para que no molesten
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialIsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authority = new ArrayList<>(2);
        authority.add(new SimpleGrantedAuthority("ROLE_"+rol));
        return new User(username, password, enabled, accountNonExpired, credentialIsNonExpired, accountNonLocked, authority);
    }
}
