package app.freegiveaway.server.domain.security;

import app.freegiveaway.server.domain.user.role.RoleName;
import app.freegiveaway.server.domain.user.role.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header=request.getHeader(SecurityConstant.HEADER_STRING);

        if (header==null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String token=header.replace(SecurityConstant.TOKEN_PREFIX, "");

        String user = JwtHelper.getUserFromToken(token);
        UserRole role = UserRole.builder()
                .roleName(RoleName.valueOf(JwtHelper.getRoleClaim(token)))
                .build();

        if (user == null) {
            throw new RuntimeException("Unable to parse user from token.");
        }

        return new UsernamePasswordAuthenticationToken(user, null, List.of(role.grantedAuthority()));

    }
}