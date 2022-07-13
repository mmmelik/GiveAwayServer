package app.freegiveaway.server.domain.user.port.persistence;

import app.freegiveaway.server.service.User;

public interface UserPersistencePort {

    boolean isPresent(String mail);

    User register(User user);
}
