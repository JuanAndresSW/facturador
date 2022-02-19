package dev.facturador.dto.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordWithTimeoutAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String timeout;

    public UsernamePasswordWithTimeoutAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.timeout=null;
    }

    public UsernamePasswordWithTimeoutAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String timeout) {
        this(principal, credentials, authorities);
        this.timeout=timeout;
    }

    public UsernamePasswordWithTimeoutAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
        this.timeout=null;
    }

    public UsernamePasswordWithTimeoutAuthenticationToken(Object principal, Object credentials, String timeout) {
        this(principal, credentials);
        this.timeout=timeout;
    }

    public String getTimeout() {
        return timeout;
    }
}
