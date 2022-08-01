package com.project.Batnik.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Batnik.model.dto.PriorityDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriorityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL_PREFIX = "/api/v1/priority/";

    @Test
    @Sql(value = "/sql/priority/addPriority.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/priority/deletePriority.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser
    public void getPriorityById() throws Exception{
        mockMvc.perform(get(URL_PREFIX + 50)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
                /*.andExpect(jsonPath("$.name").value("test"));*/
    }

    @Test
    @Sql(value = "/sql/priority/addPriority.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/priority/deletePriority.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser
    public void editPriority() throws Exception{
        PriorityDTO priorityDTO = createPriorityDTO("important");
        mockMvc.perform(put( URL_PREFIX + "edit/" + 50)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(priorityDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        /*.andExpect(jsonPath("$.name").value("test"));*/
    }

    @Test
    @Sql(value = "/sql/priority/addPriority.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/priority/deletePriority.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithMockUser
    public void deletePriority() throws Exception{
        mockMvc.perform(delete( URL_PREFIX + "delete/" + 50)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private PriorityDTO createPriorityDTO(String name){
        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.setName(name);
        return priorityDTO;
    }



}
