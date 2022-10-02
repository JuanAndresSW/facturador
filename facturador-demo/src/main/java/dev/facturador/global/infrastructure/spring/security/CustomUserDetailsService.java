package dev.facturador.global.infrastructure.spring.security;

import dev.facturador.account.application.AccountRepository;
import dev.facturador.global.domain.CustomUserDetails;
import dev.facturador.trader.domain.Trader;
import dev.facturador.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Servicio personalizado de UserDetails(Servicio de Spring Security)
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository repository;

    /**
     * Busca un usuario personalizado con un username o email como credencial
     *
     * @param usernameOrEmail Parametro para buscar el usuario
     * @return Un {@link CustomUserDetails} usuario personalizado para Srping Security
     * @throws UsernameNotFoundException Si el usuario no existe arroja este excepcion
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var user = repository.findByOwnerUserUsernameOrOwnerUserEmail
                (usernameOrEmail, usernameOrEmail);

        if (user.isPresent()) {
            return this.userBuilder(user.get().getOwnerUser(), user.get().getOwnerTrader());
        }

        //Si llega aqui este usuario no existe
        throw new UsernameNotFoundException("Username or Email do not exist");
    }


    private UserDetails userBuilder(User user, Trader trader) {
        return new CustomUserDetails(
                trader.getTraderId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                true);
    }
}
