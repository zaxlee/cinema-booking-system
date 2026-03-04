//package com.zaxlee.cinema.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zaxlee.cinema.dto.request.LoginRequest;
//import com.zaxlee.cinema.security.JwtService;
//import com.zaxlee.cinema.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(AuthController.class)
//class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private JwtService jwtService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//å
//    @Test
//    void shouldReturnTokenWhenLoginSuccessful() throws Exception {
//
//        LoginRequest request = new LoginRequest();
//        request.setUsername("admin");
//        request.setPassword("admin123");
//
//        when(jwtService.generateToken("admin", null)).thenReturn("mocked-token");
//
//        mockMvc.perform(post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isUnauthorized());
//    }
//}