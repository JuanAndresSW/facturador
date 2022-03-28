package dev.facturador.auth.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

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

    private long id;
    private String username;
    private String password;
    private String email;
    private int active;
    private int passive;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean enabled;

    public CustomUserDetails(long id,
                             String username,
                             String password,
                             String email,
                             int active,
                             int passive,
                             Collection<? extends GrantedAuthority> authorities,
                             Boolean enable) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.passive = passive;
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
