package app.freegiveaway.server.adapter.jpa.user.role;

import app.freegiveaway.server.domain.user.role.RoleName;
import app.freegiveaway.server.domain.user.role.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleJpaRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRoleName(RoleName roleName);


}
