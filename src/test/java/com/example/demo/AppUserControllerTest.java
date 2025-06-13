package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.GET;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AppUserControllerTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        appUserRepository.deleteAll();
        AppUser appUser = new AppUser();
        appUser.setUsername("admin");
        appUser.setPassword("admin");
        appUser.setRole("ADMIN");
        appUser.setConsentGiven(true);
        appUserRepository.save(appUser);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createUser() throws Exception {
        UserDTO dto = new UserDTO("newuser", "Password123!!", "USER", true);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
