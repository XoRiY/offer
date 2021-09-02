package com.airfrance.offer.domain

import com.airfrance.offer.domain.common.model.QueryResponse

import com.airfrance.offer.domain.model.Gender
import com.airfrance.offer.domain.model.UserBean
import com.airfrance.offer.domain.repository.UserRepository
import com.airfrance.offer.domain.repository.model.User
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
    private UserRepository userRepository


    def "when get is performed then the response has status 200 and return entire Object"()
    {
        Mockito.when(userRepository.findById(5L)).thenReturn(getUser())


        expect:
        "Status is 200 and return entire objectBody"
        mvc.perform( get("/users/{id}", 5))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "{\"status\":\"OK\",\"timestamp\":null,\"objectBody\":{\"id\":5,\"name\":\"john\",\"birthDate\":\"12-12-2000\",\"countryOfResidence\":\"france\",\"phoneNumber\":\"0606060606\",\"gender\":\"Male\"},\"errors\":null}"

    }


    private static Optional<User> getUser() {

        User user = User.builder()
                .name("john")
                .birthDate(LocalDate.of(2000, 12, 12))
                .gender(Gender.M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .id(5L)
                .build()

        return Optional.of(user)
    }
}


