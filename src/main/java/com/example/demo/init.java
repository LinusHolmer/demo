package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class init {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public init(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initMethod() {
        if(appUserRepository.findByUsername("user") == null) {
            AppUser user = new AppUser();
            user.setUsername("user");

            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ADMIN");
            appUserRepository.save(user);
        }

    }
}
