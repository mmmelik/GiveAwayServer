package app.freegiveaway.server.domain.user.role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Getter
@Setter
@Builder
@ToString
public class UserRole {

    private Long id;
    private RoleName roleName;

    public GrantedAuthority grantedAuthority(){
        return new SimpleGrantedAuthority(roleName.name());
    }
}
