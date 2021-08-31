package com.airfrance.offer.domain

import com.airfrance.offer.domain.common.model.QueryResponse

import com.airfrance.offer.domain.model.Gender
import com.airfrance.offer.domain.model.UserBean
import com.airfrance.offer.domain.service.UserService
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class WebControllerGroovyTest extends Specification {

    @Autowired
    private MockMvc mvc

    @MockBean
    private UserService userService

    def "when get is performed then the response has status 200 and return entire Object"()


    {
        Mockito.when(userService.getUser(5L)).thenReturn(getQueryResponse())
        expect:
        "Status is 200 and return entire objectBody"
        mvc.perform( get("/users/{id}", 5))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "{\"status\":\"OK\",\"objectBody\":{\"id\":5,\"name\":\"tahar\",\"birthDate\":\"01-01-1987\",\"countryOfResidence\":\"France\",\"phoneNumber\":\"0678901234\",\"gender\":\"Male\"},\"errors\":null}"
    }

    private static QueryResponse<UserBean> getQueryResponse() {
        UserBean userBean = UserBean.builder()
                .name("tahar")
                .birthDate(LocalDate.of(1987, 01, 01))
                .gender(Gender.M)
                .phoneNumber("0678901234")
                .countryOfResidence("France")
                .id(5L)
                .build()
        QueryResponse<UserBean> userQueryResponse = new QueryResponse<UserBean>().setObjectBody(userBean)
        userQueryResponse.setStatus(HttpStatus.OK)
        return userQueryResponse
    }
}


