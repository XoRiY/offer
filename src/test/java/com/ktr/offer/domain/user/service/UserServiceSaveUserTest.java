package com.ktr.offer.domain.user.service;

import com.ktr.offer.domain.common.exception.BadContentException;
import com.ktr.offer.domain.user.mapper.UserMapper;
import com.ktr.offer.domain.user.model.UserBean;
import com.ktr.offer.domain.user.repository.UserRepository;
import com.ktr.offer.domain.user.repository.model.User;
import com.ktr.offer.domain.user.model.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

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

       UserBean userBean = userService.saveUser(getUserBean());

        assertNotNull(user);

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
                .gender(Gender.M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .id(1L)
                .build();
    }


    private UserBean getUserBean() {
        return UserBean.builder()
                .name("john")
                .birthDate(LocalDate.of(2000, 12, 12))
                .gender(Gender.M)
                .phoneNumber("0606060606")
                .countryOfResidence("france")
                .build();
    }

}
