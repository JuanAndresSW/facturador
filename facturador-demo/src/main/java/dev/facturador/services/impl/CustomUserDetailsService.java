package dev.facturador.services.impl;

import dev.facturador.dto.security.CustomUserDetails;
import dev.facturador.entities.Usuarios;
import dev.facturador.repository.ICuentaPrincipalRepository;
import dev.facturador.repository.ICuentaSecundariaRepository;
import dev.facturador.services.IMainAccountService;
import dev.facturador.services.ISecondaryAccountService;
import lombok.RequiredArgsConstructor;
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

/**
 * Servicio personalizado de UserDetails(Servicio de Spring Security)
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ICuentaPrincipalRepository repositoryMain;
    @Autowired
    private ICuentaSecundariaRepository repositorySecondary;

    /**
     * Comprueba si el username existe en la base de datos
     * luego crea un usuario personalizado para comprobaciones con Spring Security
     * @param username Credencial del usuario ah comprobar
     * @return Retorna un UserDetails el cual es casteado a CustomUserDetails
     * @throws UsernameNotFoundException Excepcion arrojada en caso de no existir este usuario
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Revisa primero si es MAIN
        var userMainExit= repositoryMain.findByUsername(username);
        if(userMainExit.isPresent()){
            return this.userBuilder(userMainExit.get().getUserMainAccount(), "MAIN");
        }
        //Si no es MAIN entonces es secundaria
        var userSecondExit = repositorySecondary.findByUsername(username);
        if(userSecondExit.isPresent()){
            return this.userBuilder(userSecondExit.get().getUserSecondaryAccount(), "SECONDARY");
        }
        //Si ambas dan null
        throw new UsernameNotFoundException("Username dow not exist");
    }

    /**
     * Crea el CustomUserDetails
     * @param user Usuario del cual saca las credenciales necesarias
     * @param rol Rol de este usuario en la aplicacion
     * @return Retorna un CustomUserDetails
     */
    private UserDetails userBuilder(Usuarios user, String rol){
        List<GrantedAuthority> authorities = new ArrayList<>(2);
        authorities.add(new SimpleGrantedAuthority("ROLE_"+rol));

        return new CustomUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(), authorities);
    }
}
