package dev.facturador.shared.infrastructure.auth;

import dev.facturador.branchaccount.domain.IBranchAccountRepository;
import dev.facturador.mainaccount.domain.MainAccountRepository;
import dev.facturador.shared.domain.CustomUserDetails;
import dev.facturador.shared.domain.CustomUserRole;
import dev.facturador.trader.domain.Trader;
import dev.facturador.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Set;

/**
 * Servicio personalizado de UserDetails(Servicio de Spring Security)
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MainAccountRepository repositoryMain;
    @Autowired
    private IBranchAccountRepository repositoryBranch;

    /**
     * Busca un usuario personalizado con un newUsername o email como credencial
     *
     * @param usernameOrEmail Parametro para crear el usuario
     * @return Un {@link CustomUserDetails} usuario personalizado para Srping Security
     * @throws UsernameNotFoundException Si el usuario no existe arroja este excepcion
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var userMainAccount = repositoryMain.findByUserMainAccountUsernameOrUserMainAccountEmail
                (usernameOrEmail, usernameOrEmail);

        if (userMainAccount.isPresent()) {
            return this.userBuilder(userMainAccount.get().getUserMainAccount(), userMainAccount.get().getAccountOwner(), CustomUserRole.MAIN);
        }

        var userBranchAccount = repositoryBranch.findByUserBranchAccountUsernameOrUserBranchAccountEmail
                (usernameOrEmail, usernameOrEmail);
        if (userBranchAccount.isPresent()) {
            return this.userBuilder(userBranchAccount.get().getUserBranchAccount(), null, CustomUserRole.BRANCH);
        }

        //Si llega aqui este usuario no existe
        throw new UsernameNotFoundException("Username or Email do not exist");
    }


    private UserDetails userBuilder(User user, Trader trader, CustomUserRole rol) {
        Set<GrantedAuthority> authoritySet = Collections.singleton(new SimpleGrantedAuthority(rol.name()));
        if (rol.equals(CustomUserRole.MAIN)) {
            return new CustomUserDetails(
                    trader.getIdTrader(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    trader.getActive(),
                    trader.getPassive(),
                    authoritySet,
                    true);
        }

        return new CustomUserDetails(
                null,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                null,
                null,
                authoritySet,
                true);
    }
}
