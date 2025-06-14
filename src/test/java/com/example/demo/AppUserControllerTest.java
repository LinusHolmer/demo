package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;


    @BeforeEach
    void setUp() {
        appUserRepository.deleteAll();
        AppUser adminUser = new AppUser();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.setRole("ADMIN");
        adminUser.setConsentGiven(true);
        appUserRepository.save(adminUser);

    }

    @Test
    void createUser() throws Exception {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Authentication mockAuth = new UsernamePasswordAuthenticationToken("admin", null, authorities);

        String jwt = tokenService.generateToken(mockAuth);

        UserDTO dto = new UserDTO("newuser", "Password123!!", "USER", true);

        mockMvc.perform(post("/api/users")
                        .header("Authorization", "Bearer " +jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void DeleteUser() throws Exception {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Authentication mockAuth = new UsernamePasswordAuthenticationToken("admin", null, authorities);

        String jwt = tokenService.generateToken(mockAuth);

        mockMvc.perform(delete("/api/users/" + appUserRepository.findByUsername("admin").getId())
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(status().isNoContent());

    }

    @Test
    void loginTest() throws Exception {
        String loginJson = """
    {
        "username": "admin",
        "password": "admin"
    }
    """;

        mockMvc.perform(post("/request-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(not(emptyString())));
    }



}