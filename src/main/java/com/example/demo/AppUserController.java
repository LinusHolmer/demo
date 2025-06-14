package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * AppUserController handles http request like POST and GET, so it can communicate with client apps
 */

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;


    public AppUserController(AppUserRepository appUserRepository, AppUserService appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;

    }

    /**
     * Returns a list of all users
     *
     * @return list of AppUsers
     */
    @GetMapping
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    /**
     * Creates a new user with registerUser
     *
     * @param dto - dto as param to validate
     * @return register new user if validation is successful
     */
    @PostMapping
    public AppUser createUser(@RequestBody @Valid UserDTO dto) {
        return appUserService.registerUser(dto);
    }

    /**
     * Returns the user by ID
     *
     * @param id - Users id in database
     * @return Returns user or throws if not found
     */
    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return appUserRepository.findById(id).orElseThrow();
    }

    /**
     * Deletes a user by id
     *
     * @param id - Users id in database
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

