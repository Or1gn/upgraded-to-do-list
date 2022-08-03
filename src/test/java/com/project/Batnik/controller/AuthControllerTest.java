package com.project.Batnik.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Batnik.model.RQ.RegistrationRQ;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL_PREFIX = "/api/v1/auth/";
    private static final String code = "62cb778d-8791-49b5-87c3-93b54c841e6d";

    @Test
    @Sql(value = "/sql/auth/deleteUser.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void register() throws Exception{
        RegistrationRQ registrationRQ = new RegistrationRQ(
                "testuser",
                "bavoy62502@aregods.com",
                "password"
        );
        mockMvc.perform(post(URL_PREFIX + "registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRQ)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
