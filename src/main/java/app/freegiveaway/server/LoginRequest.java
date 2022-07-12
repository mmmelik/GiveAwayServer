package app.freegiveaway.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class LoginRequest {

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    @Size(min = 6)
    private String password;
}