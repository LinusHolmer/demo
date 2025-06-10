package com.example.demo;

import com.example.demo.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggingComponent loggingComponent;


    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, LoggingComponent loggingComponent) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggingComponent = loggingComponent;
    }

    // Registers a user and loggs it
    public AppUser registerUser(UserDTO dto) {
        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setConsentGiven(dto.isConsentGiven());
        loggingComponent.logInfo("Användare registrerad: " + dto.getUsername());

        return appUserRepository.save(user);
    }
    //Delets a user and loggs it
    public void deleteUser(Long id) {
        if (!appUserRepository.existsById(id)) {
            loggingComponent.logError("Kan inte ta bort användare som inte finns, ID: " + id);
            throw new UserNotFoundException("Användare med ID " + id + " finns inte");
        }

        appUserRepository.deleteById(id);
        loggingComponent.logInfo("Användare borttagen med ID: " + id);
    }






}
