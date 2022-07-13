package app.freegiveaway.server.adapter.api.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class UserLoginRequest {

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    @Size(min = 6)
    private String password;
}