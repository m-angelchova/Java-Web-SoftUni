package bg.softuni.battleships.services;

import bg.softuni.battleships.domain.DTOs.LoginDTO;
import bg.softuni.battleships.domain.DTOs.UserRegistrationDTO;
import bg.softuni.battleships.domain.entities.User;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private LoggedUser userSession;

    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean register(UserRegistrationDTO userReg){

        //Check if passwords match
        if (!userReg.getPassword().equals(userReg.getConfirmPassword())){
            return false;
        }

        //Check unique email
        Optional<User> byEmail = this.userRepository.findByEmail(userReg.getEmail());
        if (byEmail.isPresent()){
            return false;
        }

        //Check unique username
        Optional<User> byUsername = this.userRepository.findByUsername(userReg.getUsername());
        if (byUsername.isPresent()){
            return false;
        }

        //Save User
        User user = new User();
        user.setUsername(userReg.getUsername());
        user.setEmail(userReg.getEmail());
        user.setPassword(userReg.getPassword());
        user.setFullName(userReg.getFullName());
        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {

        Optional<User> byUsernameAndPassword =
                this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (byUsernameAndPassword.isEmpty()) {
            return false;
        }

        //actualLogin
        this.userSession.login(byUsernameAndPassword.get());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() != null;
    }

    public Long getLoggedUserId() {
        return this.userSession.getId();
    }
}
