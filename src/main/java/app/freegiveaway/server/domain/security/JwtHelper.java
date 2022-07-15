package app.freegiveaway.server.domain.security;

import app.freegiveaway.server.domain.user.role.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.lang.Nullable;

import java.util.Date;

public class JwtHelper {
    public static String generateToken(String username, UserRole role){
        return Jwts.builder()
                .setSubject(username)
                .claim(SecurityConstant.CLAIM_TAG_ROLE, role.getRoleName().name())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET.getBytes())
                .compact();
    }

    public static String getUserFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SecurityConstant.SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public static String getRoleClaim(String token){
        return Jwts.parser()
                .setSigningKey(SecurityConstant.SECRET.getBytes())
                .parseClaimsJws(token).getBody().get(SecurityConstant.CLAIM_TAG_ROLE,String.class);
    }
}
