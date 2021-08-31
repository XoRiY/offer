package com.airfrance.offer.domain.service;

import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.mapper.UserBeanMapper;
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
import java.util.Optional;
import java.util.Set;

import static com.airfrance.offer.domain.model.Gender.M;
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

        QueryResponse<UserBean> userQueryResponse = userService.getUser(1L);


        assertNotNull(userQueryResponse);
        assertEquals(HttpStatus.OK, userQueryResponse.getStatus());
        assertNull(userQueryResponse.getErrors());
        assertEquals(1L, userQueryResponse.getObjectBody().getId());
        assertEquals("john", userQueryResponse.getObjectBody().getName());
        assertEquals("0606060606", userQueryResponse.getObjectBody().getPhoneNumber());
        assertEquals("france", userQueryResponse.getObjectBody().getCountryOfResidence());
        assertEquals(M, userQueryResponse.getObjectBody().getGender());
        assertEquals(LocalDate.of(2000, 12, 12), userQueryResponse.getObjectBody().getBirthDate());

        Mockito.verify(userRepository).findById(1L);
        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verify(userBeanMapper).map(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userBeanMapper);

    }


    @Test
    void testNullIDGetUser() {

        QueryResponse<UserBean> userQueryResponse = userService.getUser(null);

        assertNotNull(userQueryResponse);
        assertEquals(HttpStatus.BAD_REQUEST, userQueryResponse.getStatus());
        assertEquals(Set.of("id value must be positive"), userQueryResponse.getErrors());

        Mockito.verifyNoInteractions(userRepository);
        Mockito.verifyNoInteractions(userBeanMapper);

    }


    @Test
    void testNullReturnGetUser() {

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        QueryResponse<UserBean> userQueryResponse = userService.getUser(1L);

        assertNotNull(userQueryResponse);
        assertEquals(HttpStatus.NOT_FOUND, userQueryResponse.getStatus());
        assertNull(userQueryResponse.getErrors());
        assertNull(userQueryResponse.getObjectBody());

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