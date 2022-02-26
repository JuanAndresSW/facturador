package dev.facturador.services.impl;

import dev.facturador.bo.security.CustomUserDetails;
import dev.facturador.entities.Comerciante;
import dev.facturador.entities.Usuarios;
import dev.facturador.repository.IMainAccountRepository;
import dev.facturador.repository.IBranchAccountRepository;
import dev.facturador.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Servicio personalizado de UserDetails(Servicio de Spring Security)
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IMainAccountRepository repositoryMain;
    @Autowired
    private IBranchAccountRepository repositorySecondary;
    @Autowired
    private IUserRepository repositoryUser;

    /**
     * Busca un usuario personalizado con un username o email como credencial
     *
     * @param usernameOrEmail Parametro para crear el usuario
     * @return Un {@link CustomUserDetails} usuario personalizado apra Srping Security
     * @throws UsernameNotFoundException Si el usuario no existe arroja este excepcion
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        //Si no contiene arroba doy por echo que es un username
        if(!usernameOrEmail.contains("@")){
            var userMainExit = repositoryMain.findByUsername(usernameOrEmail);
            if (userMainExit.isPresent()) {
                return this.userBuilder(userMainExit.get().getUserMainAccount(), userMainExit.get().getAccountOwner(), "MAIN");
            }
            //Si llega aqui no es main y prueba si es secondary
            var userSecondExit = repositorySecondary.findByUsername(usernameOrEmail);
            if (userSecondExit.isPresent()) {
                return this.userBuilder(userSecondExit.get().getUserSecondaryAccount(), userSecondExit.get().getSecondaryAccountOwner().getAccountOwner(), "SECONDARY");
            }
        }
        //Si contiene doy por echo que puede ser ambos
        if(usernameOrEmail.contains("@")){
            var user = loadByUsernameOrEmail(usernameOrEmail);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("Username do not exist");
            }
            var userMainExit = repositoryMain.findByUsername(user.get().getUsername());
            if (userMainExit.isPresent()) {
                return this.userBuilder(userMainExit.get().getUserMainAccount(), userMainExit.get().getAccountOwner(), "MAIN");
            }
            //Si llega aqui no es main y prueba si es secondary
            var userSecondExit = repositorySecondary.findByUsername(user.get().getUsername());
            if (userSecondExit.isPresent()) {
                return this.userBuilder(userSecondExit.get().getUserSecondaryAccount(), userSecondExit.get().getSecondaryAccountOwner().getAccountOwner(), "SECONDARY");
            }
        }

        //Si llega aqui este usuario no existe
        throw new UsernameNotFoundException("Username do not exist");
    }


    private UserDetails userBuilder(Usuarios user, Comerciante trader, String rol) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(rol));

        return new CustomUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(), trader.getActive(), trader.getPassive(), authorities);
    }
    private Optional<Usuarios> loadByUsernameOrEmail(String usernameOrEmail) {
        return repositoryUser.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }

}
