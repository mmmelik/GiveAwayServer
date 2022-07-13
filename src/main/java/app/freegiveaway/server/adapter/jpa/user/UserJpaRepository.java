package app.freegiveaway.server.adapter.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByMail(String mail);

    boolean existsByMail(String mail);


}
