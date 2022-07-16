package app.freegiveaway.server.domain.user;

import app.freegiveaway.server.domain.user.role.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class User {

    private Long id;
    private String name;
    private String mail;
    private String password;
    private LocalDateTime creationDate;
    private UserRole userRole;
    private Boolean enabled;
    private Integer ticket;
}
