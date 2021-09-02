package com.airfrance.offer.domain;

import com.airfrance.offer.domain.model.Gender;
import com.airfrance.offer.domain.repository.UserRepository;
import com.airfrance.offer.domain.repository.model.User;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerPostTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCreated() throws Exception {

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());

        String content = "{\"name\": \"john\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"france\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.errors").value(IsNull.nullValue()));

        Mockito.verify(userRepository).save(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userRepository);

    }


    @Test
    void shouldReturnAnAdultError() throws Exception {

        String content = "{\"name\": \"john\", \"birthDate\": \"2020-08-17\", \"countryOfResidence\": \"france\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors").value("user must be adult"));


        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void shouldReturnAWrongCountryError() throws Exception {

        String content = "{\"name\": \"john\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"USA\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("user must live in France"));

        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void shouldReturnANullCountryError() throws Exception {


        String content = "{\"name\": \"john\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": null, \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("Country Of Residence may not be Null or Blank"))
                .andExpect(jsonPath("$.errors[1]").value("user must live in France"));

        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void shouldReturnABlankCountryError() throws Exception {


        String content = "{\"name\": \"john\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        List<String> listErrors = Arrays.asList("country of residence may not be Empty", "user must live in France");
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("Country Of Residence may not be Null or Blank"))
                .andExpect(jsonPath("$.errors[1]").value("user must live in France"));

        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void shouldReturnANameBlankError() throws Exception {


        String content = "{\"name\": \"\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"France\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors").value("Name may not be Null or Blank"));

        Mockito.verifyNoInteractions(userRepository);

    }

    @Test
    void shouldReturnANameNullError() throws Exception {


        String content = "{\"name\": null, \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"France\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors").value("Name may not be Null or Blank"));

        Mockito.verifyNoInteractions(userRepository);

    }


    @Test
    void shouldReturnAllErrorsUsingEmptyObject() throws Exception {

        String content = "{\"name\": null, \"birthDate\": null, \"countryOfResidence\": null, \"phoneNumber\": null,\"gender\": null}";

        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("Birth Date may not be Null"))
                .andExpect(jsonPath("$.errors[1]").value("Country Of Residence may not be Null or Blank"))
                .andExpect(jsonPath("$.errors[2]").value("Name may not be Null or Blank"))
                .andExpect(jsonPath("$.errors[3]").value("user must be adult"))
                .andExpect(jsonPath("$.errors[4]").value("user must live in France"));


        Mockito.verifyNoInteractions(userRepository);

    }


    @Test
    void shouldReturnAllErrorsUsingNullObject() throws Exception {

        String content = "{}";

        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("Birth Date may not be Null"))
                .andExpect(jsonPath("$.errors[1]").value("Country Of Residence may not be Null or Blank"))
                .andExpect(jsonPath("$.errors[2]").value("Name may not be Null or Blank"))
                .andExpect(jsonPath("$.errors[3]").value("user must be adult"))
                .andExpect(jsonPath("$.errors[4]").value("user must live in France"));

        Mockito.verifyNoInteractions(userRepository);

    }

    private User getUser() {

        return User.builder()
                .name("john")
                .birthDate(LocalDate.of(2000, 12, 12))
                .gender(Gender.M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .id(5L)
                .build();
    }

}
