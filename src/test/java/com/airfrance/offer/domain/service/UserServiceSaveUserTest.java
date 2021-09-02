package com.airfrance.offer.domain.service;

import com.airfrance.offer.domain.common.exception.BadContentException;
import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.mapper.UserMapper;
import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.repository.UserRepository;
import com.airfrance.offer.domain.repository.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import static com.airfrance.offer.domain.model.Gender.M;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceSaveUserTest {


    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    void testSaveUser() {

        User user = getUser();
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user.setId(1L));

        when(userMapper.map(Mockito.any(UserBean.class))).thenCallRealMethod();

        QueryResponse<UserBean> userQueryResponse = userService.saveUser(getUserBean());

        assertNotNull(userQueryResponse);
        assertEquals(HttpStatus.CREATED, userQueryResponse.getStatus());
        assertNull(userQueryResponse.getErrors());
        assertNull(userQueryResponse.getObjectBody());


        verify(userRepository).save(Mockito.any(User.class));
        verifyNoMoreInteractions(userRepository);
        verify(userMapper).map(Mockito.any(UserBean.class));
        verifyNoMoreInteractions(userMapper);

    }


    @Test
    void testNullSaveUser() {

        BadContentException exception = assertThrows(BadContentException.class, () -> {
            userService.saveUser(null);
        });

        assertEquals(new TreeSet<>(List.of("object may not be null")), exception.getMsgs());

        Mockito.verifyNoInteractions(userRepository);
        Mockito.verifyNoInteractions(userMapper);

    }

    @Test
    void testEmptySaveUser() {
        BadContentException exception = assertThrows(BadContentException.class, () -> {
            userService.saveUser(UserBean.builder().build());
        });

        assertEquals(
                new TreeSet<>(
                        List.of("Birth Date may not be Null", "Country Of Residence may not be Null or Blank",
                                "Name may not be Null or Blank", "user must be adult", "user must live in France")),
                exception.getMsgs());

        Mockito.verifyNoInteractions(userRepository);
        Mockito.verifyNoInteractions(userMapper);

    }


    private User getUser() {
        return User.builder()
                .name("john")
                .birthDate(LocalDate.of(2000, 12, 12))
                .gender(M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .id(1L)
                .build();
    }


    private UserBean getUserBean() {
        return UserBean.builder()
                .name("john")
                .birthDate(LocalDate.of(2000, 12, 12))
                .gender(M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .build();
    }

}
