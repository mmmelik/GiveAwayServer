package app.freegiveaway.server.adapter.api.user;

import app.freegiveaway.server.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    @Size(min = 6)
    private String password;

    public User toUser() {
        return User.builder()
                .name(username)
                .mail(mail)
                .password(password)
                .build();
    }
}
