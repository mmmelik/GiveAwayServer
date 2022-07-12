package app.freegiveaway.server.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class User {

    private Long id;
    private String name;
    private String mail;
    private String password;
    private LocalDateTime creationDate;
}
