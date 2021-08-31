package com.airfrance.offer.domain;


import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerErrorsGetTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAnErrorMessage() throws Exception {

        mockMvc.perform(get("/users/{id}", -1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("id value must be positive"))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()));


        mockMvc.perform(get("/users/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("id value must be positive"))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()));

        mockMvc.perform(get("/users/{id}", -999)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("id value must be positive"))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()));

    }

}