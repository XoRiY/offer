package com.airfrance.offer.domain.service;

import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.common.model.Status;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
        assertEquals(Status.OK, userQueryResponse.getStatus());
        assertNull(userQueryResponse.getErrors());
        assertNull(userQueryResponse.getObjectBody());


        verify(userRepository).save(Mockito.any(User.class));
        verifyNoMoreInteractions(userRepository);
        verify(userMapper).map(Mockito.any(UserBean.class));
        verifyNoMoreInteractions(userMapper);

    }


    @Test
    void testNullSaveUser() {

        QueryResponse<UserBean> userQueryResponse = userService.saveUser(null);

        assertNotNull(userQueryResponse);
        assertEquals(Status.BAD_CONTENT, userQueryResponse.getStatus());
        assertEquals(Set.of("Element userBean is null"), userQueryResponse.getErrors());
        assertNull(userQueryResponse.getObjectBody());

        Mockito.verifyNoInteractions(userRepository);
        Mockito.verifyNoInteractions(userMapper);

    }

    @Test
    void testEmptySaveUser() {

        QueryResponse<UserBean> userQueryResponse = userService.saveUser(UserBean.builder().build());

        assertNotNull(userQueryResponse);
        assertEquals(Status.BAD_CONTENT, userQueryResponse.getStatus());
        assertEquals(5, userQueryResponse.getErrors().size());


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
