package app.freegiveaway.server.adapter.jpa.user.role;

import app.freegiveaway.server.adapter.jpa.common.BaseEntity;
import app.freegiveaway.server.domain.user.role.RoleName;
import app.freegiveaway.server.domain.user.role.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleName roleName;

    public static UserRoleEntity fromUserRole(UserRole userRole) {
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.id=userRole.getId();
        userRoleEntity.roleName=userRole.getRoleName();
        return userRoleEntity;
    }

    public UserRole toUserRole() {
        return UserRole.builder()
                .id(id)
                .roleName(roleName)
                .build();
    }
}
