package dev.facturador.auth.application;

import dev.facturador.auth.domain.CustomUserDetails;
import dev.facturador.auth.domain.CustomUserRole;
import dev.facturador.branchaccount.domain.IBranchAccountRepository;
import dev.facturador.mainaccount.domain.IMainAccountRepository;
import dev.facturador.trader.domain.Comerciante;
import dev.facturador.user.domain.Usuarios;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Servicio personalizado de UserDetails(Servicio de Spring Security)
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IMainAccountRepository repositoryMain;
    @Autowired
    private IBranchAccountRepository repositoryBranch;

    /**
     * Busca un usuario personalizado con un username o email como credencial
     *
     * @param usernameOrEmail Parametro para crear el usuario
     * @return Un {@link CustomUserDetails} usuario personalizado para Srping Security
     * @throws UsernameNotFoundException Si el usuario no existe arroja este excepcion
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var userMainExit = repositoryMain.findByUserMainAccountUsernameOrUserMainAccountEmail
                (usernameOrEmail, usernameOrEmail);
        if (userMainExit.isPresent()) {
            return this.userBuilder(userMainExit.get().getUserMainAccount(), userMainExit.get().getAccountOwner(), CustomUserRole.MAIN);
        }

        var userSecondExit = repositoryBranch.findByUserBranchAccountUsernameOrUserBranchAccountEmail
                (usernameOrEmail, usernameOrEmail);
        if (userSecondExit.isPresent()) {
            return this.userBuilder(userSecondExit.get().getUserBranchAccount(), userSecondExit.get().getAccountBranchOwner().getAccountOwner(), CustomUserRole.BRANCH);
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
