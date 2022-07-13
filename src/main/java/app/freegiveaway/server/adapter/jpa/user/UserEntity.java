package app.freegiveaway.server.adapter.jpa.user;

import app.freegiveaway.server.adapter.jpa.common.BaseEntity;
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
}
