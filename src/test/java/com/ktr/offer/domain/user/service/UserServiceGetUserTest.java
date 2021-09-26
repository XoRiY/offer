package com.ktr.offer.domain.user.service;

import com.ktr.offer.domain.common.exception.ResourceNotFoundException;
import com.ktr.offer.domain.user.mapper.UserBeanMapper;
import com.ktr.offer.domain.user.model.UserBean;
import com.ktr.offer.domain.user.repository.UserRepository;
import com.ktr.offer.domain.user.repository.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.ktr.offer.domain.user.model.Gender.M;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceGetUserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserBeanMapper userBeanMapper;

    @Test
    void testGetUser() {

        User user = getUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userBeanMapper.map(user)).thenCallRealMethod();

        UserBean userBean = userService.getUser(1L);


        assertNotNull(userBean);

        assertEquals(1L, userBean.getId());
        assertEquals("john", userBean.getName());
        assertEquals("0606060606", userBean.getPhoneNumber());
        assertEquals("france", userBean.getCountryOfResidence());
        assertEquals(M, userBean.getGender());
        assertEquals(LocalDate.of(2000, 12, 12), userBean.getBirthDate());

        Mockito.verify(userRepository).findById(1L);
        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verify(userBeanMapper).map(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userBeanMapper);

    }


    @Test
    void testNullIDGetUser() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getUser(-999L);
        });

        assertEquals("id value must be positive", exception.getMessage());

        Mockito.verifyNoInteractions(userRepository);
        Mockito.verifyNoInteractions(userBeanMapper);

    }


    @Test
    void testNullReturnGetUser() {


        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUser(1L);
        });

        assertEquals("no resource was found for id :1", exception.getMessage());

        Mockito.verify(userRepository).findById(1L);
        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verifyNoInteractions(userBeanMapper);

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

}