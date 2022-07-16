package app.freegiveaway.server.domain.user;

import app.freegiveaway.server.domain.port.persistence.UserPersistencePort;
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
        //Data defaults
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(userPersistencePort.getDefaultRole());
        user.setEnabled(true);

        return userPersistencePort.save(user);

    }

    public User findUserByMail(String mail) {
        return userPersistencePort.findUserByMail(mail);
    }
}
