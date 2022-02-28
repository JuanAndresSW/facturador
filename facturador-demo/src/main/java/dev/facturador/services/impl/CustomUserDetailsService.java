package dev.facturador.services.impl;

import dev.facturador.bo.security.CustomUserDetails;
import dev.facturador.bo.security.CustomUserRole;
import dev.facturador.entities.Comerciante;
import dev.facturador.entities.Usuarios;
import dev.facturador.repository.IMainAccountRepository;
import dev.facturador.repository.IBranchAccountRepository;
import dev.facturador.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Servicio personalizado de UserDetails(Servicio de Spring Security)
 */
@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IMainAccountRepository repositoryMain;
    @Autowired
    private IBranchAccountRepository repositoryBranch;
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
        log.info("---ENTRO AL LOAD USER BY USERNAME---");
        var main = repositoryMain.findByUserMainAccountUsernameOrUserMainAccountEmail(usernameOrEmail, usernameOrEmail);
        main.ifPresent(cuentaPrincipal -> log.info("---MAIN ACCUENT IS: {}", cuentaPrincipal.toString()));

        var user = repositoryUser.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username do not exist");
        }
        log.info("----EL USER SI EXISTE----");
        var userMainExit = repositoryMain.findByUsername(user.get().getUsername());
        if (userMainExit.isPresent()) {
            return this.userBuilder(userMainExit.get().getUserMainAccount(), userMainExit.get().getAccountOwner(), CustomUserRole.MAIN);
        }

        var userSecondExit = repositoryBranch.findByUsername(user.get().getUsername());
        if (userSecondExit.isPresent()) {
            return this.userBuilder(userSecondExit.get().getUserSecondaryAccount(), userSecondExit.get().getSecondaryAccountOwner().getAccountOwner(), CustomUserRole.BRANCH);
        }

        //Si llega aqui este usuario no existe
        throw new UsernameNotFoundException("Username do not exist");
    }


    private UserDetails userBuilder(Usuarios user, Comerciante trader, CustomUserRole rol) {
        return new CustomUserDetails(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                trader.getActive(),
                trader.getPassive(),
                CustomUserRole.MAIN,
                true);
    }
}
