package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Extends JpaRepository to get standard CRUD operations
 * to access AppUser from db
 */

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    /**
     * finds user by username
     *
     * @param username - users username
     * @return AppUser in database
     */
    AppUser findByUsername(String username);
}

