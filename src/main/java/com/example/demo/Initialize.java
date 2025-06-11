package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * initializes a user after booting the app if there is none
 */

@Component
public class Initialize {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public Initialize(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * registers a user if there is not a user called user in db
     */
    @PostConstruct
    public void init() {
        if(appUserRepository.findByUsername("user") == null) {
            AppUser user = new AppUser();
            user.setUsername("user");

            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ADMIN");
            appUserRepository.save(user);
        }

    }
}
