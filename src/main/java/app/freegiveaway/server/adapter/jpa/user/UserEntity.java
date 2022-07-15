package app.freegiveaway.server.adapter.jpa.user;

import app.freegiveaway.server.adapter.jpa.common.BaseEntity;

import app.freegiveaway.server.adapter.jpa.user.role.UserRoleEntity;
import app.freegiveaway.server.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity {


    private String name;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private UserRoleEntity userRole;

    @ColumnDefault("1")
    private Boolean enabled;

    public static UserEntity fromUser(User user) {
        UserEntity entity=new UserEntity();
        entity.id=user.getId();
        entity.mail= user.getMail();
        entity.name=user.getName();
        entity.password= user.getPassword();
        entity.creationDate=user.getCreationDate();
        entity.userRole=UserRoleEntity.fromUserRole(user.getUserRole());
        entity.enabled=user.getEnabled();
        return entity;
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .mail(mail)
                .password(password)
                .name(name)
                .creationDate(creationDate)
                .userRole(userRole.toUserRole())
                .enabled(enabled)
                .build();
    }
}
