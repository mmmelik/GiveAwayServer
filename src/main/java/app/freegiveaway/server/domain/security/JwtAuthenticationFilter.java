package app.freegiveaway.server.domain.security;

import app.freegiveaway.server.adapter.api.user.UserLoginRequest;
import app.freegiveaway.server.domain.user.role.RoleName;
import app.freegiveaway.server.domain.user.role.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/user/login"); //required to log in using this specific path
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginRequest userLoginRequest =new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
            log.info(userLoginRequest.toString());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getMail(), userLoginRequest.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to map request body");
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserRole userRole=UserRole.builder()
                .roleName(authResult.getAuthorities().isEmpty()?RoleName.USER:RoleName.valueOf(authResult.getAuthorities().stream().findAny().get().getAuthority()))
                .build();

        String token = JwtHelper.generateToken(authResult.getName(),userRole);
        response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + token);
        log.info("User "+authResult.getName()+" successfully authenticated with role "+ userRole + " (/api)");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        log.info("User failed to authenticate. Cause: "+failed.getMessage());
    }
}
