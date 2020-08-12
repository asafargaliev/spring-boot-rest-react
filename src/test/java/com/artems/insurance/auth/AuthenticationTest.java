package com.artems.insurance.auth;

import com.artems.insurance.dto.AuthenticationRequestDto;
import com.artems.insurance.model.User;
import com.artems.insurance.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .username("testUser")
                .password("$2a$04$Mssud3YK3ghL72a9LQubQ.J1hDxPDBDTWHPydQ8pBMypzFHtGJFNa")
                .roles(Arrays.asList("USER"))
                .build();

        when(userRepository.findByUsername(mockUser.getUsername()))
                .thenReturn(Optional.of(mockUser));


        // FAIL LOGIN
        AuthenticationRequestDto authenticationRequestDto = AuthenticationRequestDto.builder()
                .username(mockUser.getUsername())
                .password("test1")
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .content(asJsonString(authenticationRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isForbidden());

        // SUCCESS LOGIN
        authenticationRequestDto.setPassword("test");
        RequestBuilder requestBuilderSuccess = MockMvcRequestBuilders.post("/auth/login")
                .content(asJsonString(authenticationRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilderSuccess)
                .andExpect(status().isOk());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
