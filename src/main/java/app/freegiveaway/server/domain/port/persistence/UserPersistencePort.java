package app.freegiveaway.server.domain.port.persistence;


import app.freegiveaway.server.domain.user.User;
import app.freegiveaway.server.domain.user.role.UserRole;

public interface UserPersistencePort {

    boolean isPresent(String mail);

    User register(User user);

    User findUserByMail(String mail);

    UserRole getDefaultRole();
}
