package dev.facturador.global.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;

/**
 * User Details Personalizado
 */
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long traderId;
    private String username;
    private String password;
    private String email;
    private Integer actives;
    private Integer passives;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean enabled;

    public CustomUserDetails(Long traderId,
                             String username,
                             String password,
                             String email,
                             Integer actives,
                             Integer passives,
                             Collection<? extends GrantedAuthority> authorities,
                             Boolean enable) {
        this.traderId = traderId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.actives = actives;
        this.passives = passives;
        this.authorities = authorities;
        this.enabled = enable;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null ? null : authorities.stream().toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
