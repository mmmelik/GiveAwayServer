package app.freegiveaway.server.adapter.jpa.user;

import app.freegiveaway.server.domain.user.port.persistence.UserPersistencePort;
import app.freegiveaway.server.service.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean isPresent(String mail) {
        return userJpaRepository.existsByMail(mail);
    }

    @Override
    public User register(User user) {
        return userJpaRepository.save(UserEntity.fromUser(user)).toUser();
    }
}
