package com.ktr.offer.domain;


import com.ktr.offer.domain.user.model.Gender;
import com.ktr.offer.domain.user.repository.UserRepository;
import com.ktr.offer.domain.user.repository.model.User;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerGetTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnACorrectObject() throws Exception {

        Mockito.when(userRepository.findById(5L)).thenReturn(getUser());

        mockMvc.perform(
                        get("/users/{id}", 5)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("5"))
                .andExpect(jsonPath("$.name").value("john"))
                .andExpect(jsonPath("$.birthDate").value("12-12-2000"))
                .andExpect(jsonPath("$.countryOfResidence").value("france"))
                .andExpect(jsonPath("$.phoneNumber").value("0606060606"))
                .andExpect(jsonPath("$.gender").value("Male")
                );


        Mockito.verify(userRepository).findById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(userRepository);

    }

    private Optional<User> getUser() {

        User user = User.builder()
                .name("john")
                .birthDate(LocalDate.of(2000, 12, 12))
                .gender(Gender.M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .id(5L)
                .build();

        return Optional.of(user);
    }


}