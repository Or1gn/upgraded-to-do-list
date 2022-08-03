package com.project.Batnik.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL_PREFIX = "/api/v1/project/";

    @Test
    @Sql(value = "/sql/project/addProject.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/project/deleteProject.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser
    public void getProject() throws Exception {
        mockMvc.perform(get(URL_PREFIX + 50)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        /*.andExpect(jsonPath("$.name").value("test"));*/
    }

    @Test
    @Sql(value = "/sql/project/addProject.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/project/deleteProject.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser
    public void getLink() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "get_link/" + 50)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        /*.andExpect(jsonPath("$.name").value("test"));*/
    }
}
