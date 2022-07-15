package app.freegiveaway.server.adapter.jpa.user;

import app.freegiveaway.server.adapter.jpa.user.role.UserRoleEntity;
import app.freegiveaway.server.adapter.jpa.user.role.UserRoleJpaRepository;
import app.freegiveaway.server.domain.port.persistence.UserPersistencePort;
import app.freegiveaway.server.domain.user.User;
import app.freegiveaway.server.domain.user.role.RoleName;
import app.freegiveaway.server.domain.user.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserJpaRepository userJpaRepository;

    private final UserRoleJpaRepository userRoleJpaRepository;

    @Override
    public boolean isPresent(String mail) {
        return userJpaRepository.existsByMail(mail);
    }

    @Override
    public User register(User user) {
        return userJpaRepository.save(UserEntity.fromUser(user)).toUser();
    }

    @Override
    public User findUserByMail(String mail) {
        Optional<UserEntity> optionalUser=userJpaRepository.findByMail(mail);

        if (optionalUser.isEmpty()){
            throw new RuntimeException("User "+mail+" not found.");
        }

        return optionalUser.get().toUser();
    }

    @Override
    public UserRole getDefaultRole() {
        Optional<UserRoleEntity> optionalUserRole=userRoleJpaRepository.findByRoleName(RoleName.USER);
        if (optionalUserRole.isEmpty()){
            UserRoleEntity userRoleEntity=new UserRoleEntity();
            userRoleEntity.setRoleName(RoleName.USER);
            return userRoleJpaRepository.save(userRoleEntity).toUserRole();
        }
        return optionalUserRole.get().toUserRole();
    }
}
