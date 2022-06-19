package dev.facturador.global.infrastructure.springservice;

import dev.facturador.account.domain.AccountRepository;
import dev.facturador.global.domain.CustomUserDetails;
import dev.facturador.trader.domain.Trader;
import dev.facturador.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Servicio personalizado de UserDetails(Servicio de Spring Security)
 */
@Slf4j
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
        var userMainAccount = repository.findByOwnerUserUsernameOrOwnerUserEmail
                (usernameOrEmail, usernameOrEmail);

        if (userMainAccount.isPresent()) {
            return this.userBuilder(userMainAccount.get().getOwnerUser(), userMainAccount.get().getOwnerTrader());
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
                trader.getActives(),
                trader.getPassives(),
                null,
                true);
    }
}
