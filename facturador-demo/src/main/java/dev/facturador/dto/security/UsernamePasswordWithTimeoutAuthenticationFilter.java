package dev.facturador.dto.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsernamePasswordWithTimeoutAuthenticationFilter extends
        UsernamePasswordAuthenticationFilter {

    @Value("timeout")
    private String timeoutParameter;
    private boolean postOnly;

    @Override
    public void setPostOnly(boolean postOnly) {
        super.setPostOnly(postOnly);
        this.postOnly = postOnly;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (postOnly && !"POST".equals(request.getMethod())) {
            StringBuilder message = new StringBuilder();
            message.append("Authentication method not supported: ").append(request.getMethod());
            throw new AuthenticationServiceException(message.toString());
        }

        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        final String timeout = this.obtainTimeout(request);

        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }

        username = username.trim();

        var authRequest = new UsernamePasswordWithTimeoutAuthenticationToken(username, password, timeout);

        this.setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainTimeout(HttpServletRequest request) {
        return request.getParameter(timeoutParameter);
    }

    public void setTimeoutParameter(String timeoutParameter) {
        this.timeoutParameter = timeoutParameter;
    }
}
