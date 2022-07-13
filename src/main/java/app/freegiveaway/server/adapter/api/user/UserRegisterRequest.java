package app.freegiveaway.server.adapter.api.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    @Size(min = 6)
    private String password;

}
