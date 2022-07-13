package app.freegiveaway.server.domain.user;

import app.freegiveaway.server.domain.user.port.persistence.UserPersistencePort;
import app.freegiveaway.server.service.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserPersistencePort userPersistencePort;

    public User registerUser(User user){
        if (userPersistencePort.isPresent(user.getMail())){
            throw new RuntimeException("User " +user.getMail()+ " already registered.");
        }
        //Encode Password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userPersistencePort.register(user);

    }
}
