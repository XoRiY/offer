package com.airfrance.offer.domain;


import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.common.model.Status;
import com.airfrance.offer.domain.model.Gender;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerGetTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldReturnACorrectObject() throws Exception {

        Mockito.when(userService.getUser(5L)).thenReturn(getQueryResponse());

        mockMvc.perform(
                        get("/users/{id}", 5)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.errors").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.objectBody.id").value("1"))
                .andExpect(jsonPath("$.objectBody.name").value("john"))
                .andExpect(jsonPath("$.objectBody.birthDate").value("12-12-2000"))
                .andExpect(jsonPath("$.objectBody.countryOfResidence").value("france"))
                .andExpect(jsonPath("$.objectBody.phoneNumber").value("0606060606"))
                .andExpect(jsonPath("$.objectBody.gender").value("Male")
                );


        Mockito.verify(userService).getUser(Mockito.anyLong());

    }

    private QueryResponse<UserBean> getQueryResponse() {
        UserBean userBean = UserBean.builder()
                .name("john")
                .birthDate(LocalDate.of(2000, 12, 12))
                .gender(Gender.M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .id(1L)
                .build();
        QueryResponse<UserBean> userQueryResponse = new QueryResponse<UserBean>().setObjectBody(userBean);
        userQueryResponse.setStatus(Status.OK);
        return userQueryResponse;
    }

}