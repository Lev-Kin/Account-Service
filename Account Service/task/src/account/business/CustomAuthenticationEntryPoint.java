package account.business;

import account.business.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.addHeader("WWW-Authenticate", "Basic realm=\"" + super.getRealmName() + "\"");

        if (authException instanceof LockedException) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "User account is locked");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User account is locked");
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
    }
}

