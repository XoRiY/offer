package com.airfrance.offer.domain;

import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.service.UserService;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerPostTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void shouldReturnOK() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenReturn(new QueryResponse<UserBean>().setStatus(HttpStatus.OK));

        String content = "{\"name\": \"0\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"france\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.errors").value(IsNull.nullValue()));

        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }


    @Test
    void shouldReturnAnAdultError() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

        String content = "{\"name\": \"0\", \"birthDate\": \"2020-08-17\", \"countryOfResidence\": \"france\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors").value("user must be adult"));


        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }

    @Test
    void shouldReturnAWrongCountryError() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

        String content = "{\"name\": \"0\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"USA\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors").value("user must live in France"));

        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }

    @Test
    void shouldReturnANullCountryError() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

        String content = "{\"name\": \"0\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": null, \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"));

        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }

    @Test
    void shouldReturnABlankCountryError() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

        String content = "{\"name\": \"0\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        List<String> listErrors = Arrays.asList("country of residence may not be Empty", "user must live in France");
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0]").value("Country Of Residence may not be Null or Blank"))
                .andExpect(jsonPath("$.errors[1]").value("user must live in France"));

        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }

    @Test
    void shouldReturnANameBlankError() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

        String content = "{\"name\": \"\", \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"France\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.objectBody").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors").value("Name may not be Null or Blank"));

        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }

    @Test
    void shouldReturnANameNullError() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

        String content = "{\"name\": null, \"birthDate\": \"2000-08-17\", \"countryOfResidence\": \"France\", \"phoneNumber\": \"0606060606\",\"gender\": \"Male\"}";
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors").value("Name may not be Null or Blank"));

        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }


    @Test
    void shouldReturnAllErrorsUsingEmptyObject() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

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


        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }


    @Test
    void shouldReturnAllErrorsUsingNullObject() throws Exception {

        Mockito.when(userService.saveUser(Mockito.any(UserBean.class))).thenCallRealMethod();

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

        Mockito.verify(userService).saveUser(Mockito.any(UserBean.class));

    }


}
