package app.freegiveaway.server.adapter.jpa.user;

import app.freegiveaway.server.adapter.jpa.common.BaseEntity;
import app.freegiveaway.server.service.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity {


    private String name;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String password;

    public static UserEntity fromUser(User user) {
        UserEntity entity=new UserEntity();
        entity.id=user.getId();
        entity.mail= user.getMail();
        entity.name=user.getName();
        entity.password= user.getPassword();
        entity.creationDate=user.getCreationDate();
        return entity;
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .mail(mail)
                .password(password)
                .name(name)
                .creationDate(creationDate)
                .build();
    }
}
