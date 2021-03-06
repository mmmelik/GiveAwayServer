package app.freegiveaway.server.adapter.api.user;

import app.freegiveaway.server.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegisterResponse {

    private Long id;

    public static UserRegisterResponse fromUser(User user) {
        return UserRegisterResponse.builder()
                .id(user.getId())
                .build();
    }
}
